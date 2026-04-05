package com.framework.suites;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * Full test suite - runs ALL tests across all packages.
 */
@Suite
@SelectPackages("com.framework.tests")
public class FullTestSuite {
}
