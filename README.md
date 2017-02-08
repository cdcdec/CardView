# CardView

> Material design中有一种很个性的设计概念：卡片式设计（Cards），这种设计与传统的List Item有所区别，Cards包含更多的内容元素和拥有自己的UI特征. 为了更好地实现这种 Cards UI 的设计，Google在v7包中引进了一种全新的控件:CardVew，一个带圆角和阴影背景的FrameLayout。CardView在Android Lollipop（API 21）及以上版本的系统中适配较好.

## 排版

CardView从本质上属于FrameLayout，而CardView通常包含了较多的内容元素，为了方便地排版布局中的各个元素，一般借助于其他基本布局容器，比如这里我们使用了一个RelativeLayout作为CardView的唯一Child.

## 阴影Padding

在Android Lollipop之前的系统，CardView会自动添加一些额外的padding空间来绘制阴影部分，这也导致了以Lollipop为分界线的不同系统上CardView的尺寸大小不同。为了解决这个问题，有两种方法：第一种，使用不同API版本的dimension资源适配（也就是借助values和values-21文件夹中不同的dimens.xml文件）；第二种，就是使用setUseCompatPadding属性，设置为true（默认值为false），让CardView在不同系统中使用相同的padding值。

## 圆角覆盖

在pre-Lollipop平台（API 21版本之前）上，CardView不会裁剪内容元素以满足圆角需求，而是使用添加padding的替代方案，从而使内容元素不会覆盖CardView的圆角。而控制这个行为的属性就是cardPreventCornerOverlap，默认值为true.默认值下自动添加padding的方式不可取，所以需要设置该属性值为false。需要注意的一点是，该属性的设置在Lollipop及以上版本的系统中没有任何影响，除非cardUseCompatPadding的值为true。

## Ripple效果

Cards一般都是可点击的，为此我们使用了foreground属性并使用系统的selectableItemBackground值，同时设置clickable为true（如果在java代码中使用了cardView.setOnClickListener，就可以不用写clickable属性了），从而达到在Lollipop及以上版本系统中实现点击时的涟漪效果（Ripple）.在pre-Lollipop版本中，则是一个普通的点击变暗的效果，如果想改变老版本的点击效果，也可以通过版本兼容的方式另行修改。

## lift-on-touch

根据官网Material motion部分对交互动作规范的指导，Cards、Button等视图应该有一个触摸抬起（lift-on-touch）的交互效果，也就是在三维立体空间上的Z轴发生位移，从而产生一个阴影加深的效果，与Ripple效果共同使用。在实现这个效果也很简单，可以在res/drawable目录下建立一个lift_on_touch.xml文件，内容如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- animate the translationZ property of a view when pressed -->
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:state_enabled="true"
        android:state_pressed="true">
        <set>
            <objectAnimator
                android:duration="@android:integer/config_shortAnimTime"
                android:propertyName="translationZ"
                android:valueTo="6dp"
                android:valueType="floatType"/>
        </set>
    </item>
    <item>
        <set>
            <objectAnimator
                android:duration="@android:integer/config_shortAnimTime"
                android:propertyName="translationZ"
                android:valueTo="0"
                android:valueType="floatType"/>
        </set>
    </item>
</selector>
```

即通过属性动画动态改变translationZ值，沿着Z轴，从0dp到6dp变化。然后将其赋值给android:stateListAnimator属性即可。由于stateListAnimator属性只适用于Lollipop及以上版本，为了隐藏xml中的版本警告，可以指定tools:targetApi="lollipop"。

## 官网资料

CardView 扩展 FrameLayout 类并让您能够显示卡片内的信息，这些信息在整个平台中拥有一致的呈现方式。CardView 小部件可拥有阴影和圆角。

如果要使用阴影创建卡片，请使用 card_view:cardElevation 属性。CardView 在 Android 5.0（API 级别 21）及更高版本中使用真实高度与动态阴影，而在早期的 Android 版本中则返回编程阴影实现。如需了解详细信息，请参阅保持兼容性

使用这些属性自定义 CardView 小部件的外观：

- 如果要在您的布局中设置圆角半径，请使用 card_view:cardCornerRadius 属性。
- 如果要在您的代码中设置圆角半径，请使用 CardView.setRadius 方法。
- 如果要设置卡片的背景颜色，请使用 card_view:cardBackgroundColor 属性。 下列代码示例将展示如何将 CardView 小部件包括在您的布局中：

  ```xml
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  xmlns:card_view="http://schemas.android.com/apk/res-auto"
  ... >
  <!-- A CardView that contains a TextView -->
  <android.support.v7.widget.CardView
     xmlns:card_view="http://schemas.android.com/apk/res-auto"
     android:id="@+id/card_view"
     android:layout_gravity="center"
     android:layout_width="200dp"
     android:layout_height="200dp"
     card_view:cardCornerRadius="4dp">

     <TextView
         android:id="@+id/info_text"
         android:layout_width="match_parent"
         android:layout_height="match_parent" />
  </android.support.v7.widget.CardView>
  </LinearLayout>
  ```
