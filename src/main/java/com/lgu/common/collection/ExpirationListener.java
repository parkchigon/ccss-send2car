package com.lgu.common.collection;

/**
 * A listener for expired object events.
 */
public interface ExpirationListener<E> {
  void expired(E expiredObject);
}
