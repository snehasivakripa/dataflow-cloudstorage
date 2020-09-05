package com.functions;

import javax.annotation.Generated;
import javax.annotation.Nullable;
import org.apache.beam.sdk.options.ValueProvider;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_RedisConnectionConfiguration extends RedisConnectionConfiguration {

  private final ValueProvider<String> host;

  private final ValueProvider<Integer> port;

  private final ValueProvider<String> auth;

  private final ValueProvider<Integer> timeout;

  private final ValueProvider<Boolean> ssl;

  private AutoValue_RedisConnectionConfiguration(
      ValueProvider<String> host,
      ValueProvider<Integer> port,
      @Nullable ValueProvider<String> auth,
      ValueProvider<Integer> timeout,
      ValueProvider<Boolean> ssl) {
    this.host = host;
    this.port = port;
    this.auth = auth;
    this.timeout = timeout;
    this.ssl = ssl;
  }

  @Override
  ValueProvider<String> host() {
    return host;
  }

  @Override
  ValueProvider<Integer> port() {
    return port;
  }

  @Nullable
  @Override
  ValueProvider<String> auth() {
    return auth;
  }

  @Override
  ValueProvider<Integer> timeout() {
    return timeout;
  }

  @Override
  ValueProvider<Boolean> ssl() {
    return ssl;
  }

  @Override
  public String toString() {
    return "RedisConnectionConfiguration{"
        + "host=" + host + ", "
        + "port=" + port + ", "
        + "auth=" + auth + ", "
        + "timeout=" + timeout + ", "
        + "ssl=" + ssl
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof RedisConnectionConfiguration) {
      RedisConnectionConfiguration that = (RedisConnectionConfiguration) o;
      return this.host.equals(that.host())
          && this.port.equals(that.port())
          && (this.auth == null ? that.auth() == null : this.auth.equals(that.auth()))
          && this.timeout.equals(that.timeout())
          && this.ssl.equals(that.ssl());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= host.hashCode();
    h$ *= 1000003;
    h$ ^= port.hashCode();
    h$ *= 1000003;
    h$ ^= (auth == null) ? 0 : auth.hashCode();
    h$ *= 1000003;
    h$ ^= timeout.hashCode();
    h$ *= 1000003;
    h$ ^= ssl.hashCode();
    return h$;
  }

  @Override
  RedisConnectionConfiguration.Builder builder() {
    return new Builder(this);
  }

  static final class Builder extends RedisConnectionConfiguration.Builder {
    private ValueProvider<String> host;
    private ValueProvider<Integer> port;
    private ValueProvider<String> auth;
    private ValueProvider<Integer> timeout;
    private ValueProvider<Boolean> ssl;
    Builder() {
    }
    private Builder(RedisConnectionConfiguration source) {
      this.host = source.host();
      this.port = source.port();
      this.auth = source.auth();
      this.timeout = source.timeout();
      this.ssl = source.ssl();
    }
    @Override
    RedisConnectionConfiguration.Builder setHost(ValueProvider<String> host) {
      if (host == null) {
        throw new NullPointerException("Null host");
      }
      this.host = host;
      return this;
    }
    @Override
    RedisConnectionConfiguration.Builder setPort(ValueProvider<Integer> port) {
      if (port == null) {
        throw new NullPointerException("Null port");
      }
      this.port = port;
      return this;
    }
    @Override
    RedisConnectionConfiguration.Builder setAuth(ValueProvider<String> auth) {
      this.auth = auth;
      return this;
    }
    @Override
    RedisConnectionConfiguration.Builder setTimeout(ValueProvider<Integer> timeout) {
      if (timeout == null) {
        throw new NullPointerException("Null timeout");
      }
      this.timeout = timeout;
      return this;
    }
    @Override
    RedisConnectionConfiguration.Builder setSsl(ValueProvider<Boolean> ssl) {
      if (ssl == null) {
        throw new NullPointerException("Null ssl");
      }
      this.ssl = ssl;
      return this;
    }
    @Override
    RedisConnectionConfiguration build() {
      String missing = "";
      if (this.host == null) {
        missing += " host";
      }
      if (this.port == null) {
        missing += " port";
      }
      if (this.timeout == null) {
        missing += " timeout";
      }
      if (this.ssl == null) {
        missing += " ssl";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_RedisConnectionConfiguration(
          this.host,
          this.port,
          this.auth,
          this.timeout,
          this.ssl);
    }
  }

}
