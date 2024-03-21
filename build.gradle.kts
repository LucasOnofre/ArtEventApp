import com.android.build.gradle.BaseExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(AndroidDependencies.gradle)
        classpath(AndroidDependencies.kotlin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    afterEvaluate {
        (extensions.findByName("android") as? BaseExtension)?.apply {
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }
}
