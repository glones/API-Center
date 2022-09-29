package com.upwork.sync.facade

interface SyncAgent {

    List<?> doSync();

    Boolean isUpdateAvailable();

    String getSupportedSubType();

}