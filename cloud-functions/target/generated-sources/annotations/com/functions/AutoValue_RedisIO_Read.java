package com.functions;

import javax.annotation.Generated;
import javax.annotation.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_RedisIO_Read extends RedisIO.Read {

  private final RedisConnectionConfiguration connectionConfiguration;

  private final String keyPattern;

  private final int batchSize;

  private final boolean outputParallelization;

  private AutoValue_RedisIO_Read(
      @Nullable RedisConnectionConfiguration connectionConfiguration,
      @Nullable String keyPattern,
      int batchSize,
      boolean outputParallelization) {
    this.connectionConfiguration = connectionConfiguration;
    this.keyPattern = keyPattern;
    this.batchSize = batchSize;
    this.outputParallelization = outputParallelization;
  }

  @Nullable
  @Override
  RedisConnectionConfiguration connectionConfiguration() {
    return connectionConfiguration;
  }

  @Nullable
  @Override
  String keyPattern() {
    return keyPattern;
  }

  @Override
  int batchSize() {
    return batchSize;
  }

  @Override
  boolean outputParallelization() {
    return outputParallelization;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof RedisIO.Read) {
      RedisIO.Read that = (RedisIO.Read) o;
      return (this.connectionConfiguration == null ? that.connectionConfiguration() == null : this.connectionConfiguration.equals(that.connectionConfiguration()))
          && (this.keyPattern == null ? that.keyPattern() == null : this.keyPattern.equals(that.keyPattern()))
          && this.batchSize == that.batchSize()
          && this.outputParallelization == that.outputParallelization();
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (connectionConfiguration == null) ? 0 : connectionConfiguration.hashCode();
    h$ *= 1000003;
    h$ ^= (keyPattern == null) ? 0 : keyPattern.hashCode();
    h$ *= 1000003;
    h$ ^= batchSize;
    h$ *= 1000003;
    h$ ^= outputParallelization ? 1231 : 1237;
    return h$;
  }

  @Override
  RedisIO.Read.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends RedisIO.Read.Builder {
    private RedisConnectionConfiguration connectionConfiguration;
    private String keyPattern;
    private Integer batchSize;
    private Boolean outputParallelization;
    Builder() {
    }
    private Builder(RedisIO.Read source) {
      this.connectionConfiguration = source.connectionConfiguration();
      this.keyPattern = source.keyPattern();
      this.batchSize = source.batchSize();
      this.outputParallelization = source.outputParallelization();
    }
    @Override
    RedisIO.Read.Builder setConnectionConfiguration(RedisConnectionConfiguration connectionConfiguration) {
      this.connectionConfiguration = connectionConfiguration;
      return this;
    }
    @Override
    RedisIO.Read.Builder setKeyPattern(String keyPattern) {
      this.keyPattern = keyPattern;
      return this;
    }
    @Override
    RedisIO.Read.Builder setBatchSize(int batchSize) {
      this.batchSize = batchSize;
      return this;
    }
    @Override
    RedisIO.Read.Builder setOutputParallelization(boolean outputParallelization) {
      this.outputParallelization = outputParallelization;
      return this;
    }
    @Override
    RedisIO.Read build() {
      String missing = "";
      if (this.batchSize == null) {
        missing += " batchSize";
      }
      if (this.outputParallelization == null) {
        missing += " outputParallelization";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_RedisIO_Read(
          this.connectionConfiguration,
          this.keyPattern,
          this.batchSize,
          this.outputParallelization);
    }
  }

}
