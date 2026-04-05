package com.framework.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite that runs only fast-tagged tests.
 */
@Suite
@SelectPackages("com.framework.tests")
@IncludeTags("fast")
public class FastTestSuite {
}
