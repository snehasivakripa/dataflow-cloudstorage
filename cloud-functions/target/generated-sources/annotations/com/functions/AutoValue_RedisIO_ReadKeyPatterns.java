package com.functions;

import javax.annotation.Generated;
import javax.annotation.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_RedisIO_ReadKeyPatterns extends RedisIO.ReadKeyPatterns {

  private final RedisConnectionConfiguration connectionConfiguration;

  private final int batchSize;

  private final boolean outputParallelization;

  private AutoValue_RedisIO_ReadKeyPatterns(
      @Nullable RedisConnectionConfiguration connectionConfiguration,
      int batchSize,
      boolean outputParallelization) {
    this.connectionConfiguration = connectionConfiguration;
    this.batchSize = batchSize;
    this.outputParallelization = outputParallelization;
  }

  @Nullable
  @Override
  RedisConnectionConfiguration connectionConfiguration() {
    return connectionConfiguration;
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
    if (o instanceof RedisIO.ReadKeyPatterns) {
      RedisIO.ReadKeyPatterns that = (RedisIO.ReadKeyPatterns) o;
      return (this.connectionConfiguration == null ? that.connectionConfiguration() == null : this.connectionConfiguration.equals(that.connectionConfiguration()))
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
    h$ ^= batchSize;
    h$ *= 1000003;
    h$ ^= outputParallelization ? 1231 : 1237;
    return h$;
  }

  @Override
  RedisIO.ReadKeyPatterns.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends RedisIO.ReadKeyPatterns.Builder {
    private RedisConnectionConfiguration connectionConfiguration;
    private Integer batchSize;
    private Boolean outputParallelization;
    Builder() {
    }
    private Builder(RedisIO.ReadKeyPatterns source) {
      this.connectionConfiguration = source.connectionConfiguration();
      this.batchSize = source.batchSize();
      this.outputParallelization = source.outputParallelization();
    }
    @Override
    RedisIO.ReadKeyPatterns.Builder setConnectionConfiguration(RedisConnectionConfiguration connectionConfiguration) {
      this.connectionConfiguration = connectionConfiguration;
      return this;
    }
    @Override
    RedisIO.ReadKeyPatterns.Builder setBatchSize(int batchSize) {
      this.batchSize = batchSize;
      return this;
    }
    @Override
    RedisIO.ReadKeyPatterns.Builder setOutputParallelization(boolean outputParallelization) {
      this.outputParallelization = outputParallelization;
      return this;
    }
    @Override
    RedisIO.ReadKeyPatterns build() {
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
      return new AutoValue_RedisIO_ReadKeyPatterns(
          this.connectionConfiguration,
          this.batchSize,
          this.outputParallelization);
    }
  }

}
