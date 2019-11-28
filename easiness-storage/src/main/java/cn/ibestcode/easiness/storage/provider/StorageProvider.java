package cn.ibestcode.easiness.storage.provider;

import cn.ibestcode.easiness.storage.Storage;

public interface StorageProvider {
  Storage getStorage();

  void clear();

  String getType();

  default boolean supports(String type) {
    return type != null && type.equalsIgnoreCase(getType());
  }
}
