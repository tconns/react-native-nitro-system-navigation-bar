#include <jni.h>
#include "nitrosystemnavigationbarOnLoad.hpp"

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void*) {
  return margelo::nitro::nitrosystemnavigationbar::initialize(vm);
}
