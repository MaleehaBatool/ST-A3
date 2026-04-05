package com.framework.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite that runs only slow-tagged (data-driven) tests.
 */
@Suite
@SelectPackages("com.framework.tests")
@IncludeTags("slow")
public class SlowTestSuite {
}
