# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Hilt
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.android.AndroidEntryPoint class * { *; }
-keepnames @dagger.hilt.android.AndroidEntryPoint class *

# Retrofit & Gson
-keep class com.diva.recipeapp.core.data.source.remote.response.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keep class com.google.gson.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *

# Domain Model (Parcelable)
-keep class com.diva.recipeapp.core.domain.model.** { *; }
-keep class com.diva.recipeapp.core.data.source.local.entity.** { *; }

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# Coroutines
-keep class kotlinx.coroutines.** { *; }

-keep class net.sqlcipher.database.** { *; }