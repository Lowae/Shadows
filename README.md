# Shadows
一个简单且可自定义的实现类似CSS样式阴影的Android库

A simple and customizable library on Android to implement CSS style shadows.

# Gradle
Step 1. Add the JitPack repository to your build file

```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.Lowae:Shadows:1.0.1-alpha02'
}
```

# Usage

```xml
    <declare-styleable name="ShadowViews">
        <attr name="shadow_radius" format="dimension" />
        <attr name="shadow_color" format="color" />
        <attr name="shadow_dx" format="dimension" />
        <attr name="shadow_dy" format="dimension" />
        <attr name="shadow_inset" format="dimension" />
        <attr name="shadow_corner" format="dimension" />
        <attr name="shadow_corner_tl" format="dimension" />
        <attr name="shadow_corner_tr" format="dimension" />
        <attr name="shadow_corner_bl" format="dimension" />
        <attr name="shadow_corner_br" format="dimension" />
        <!-- 作用于阴影上层的background，因为绘制流程的原因无法自定义控制background的bound，所以需要替换android:background属性 -->
        <attr name="shadow_background" format="reference|color" />
        <!-- 自动设置预留阴影的padding，默认值为true，如果不需要可设置为false但是需要自己设置预留空间或者设置clipChildren=false属性 -->
        <attr name="include_shadow_padding" format="boolean" />
    </declare-styleable>
```
```xml
    <com.lowae.shadows.widgets.ShadowFrameLayout
        android:id="@+id/shadow_layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginBottom="100dp"
        app:include_shadow_padding="true"
        app:shadow_color="#8BC34A"
        app:shadow_corner="0dp"
        app:shadow_dx="0dp"
        app:shadow_dy="0dp"
        app:shadow_inset="0dp"
        app:shadow_radius="20dp">

        <ImageView
            android:id="@+id/image"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:scaleType="centerCrop"
            android:src="@drawable/img" />

    </com.lowae.shadows.widgets.ShadowFrameLayout>
```
<img src="/pictures/screenshot1.png" width="50%">
