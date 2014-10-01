package mz.inolabdev.rh;

import mz.inolabdev.rh.services.EventServiceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({// @formatter:off
	EventServiceTest.class
    // manual only
    // ,FooServiceSortingWitNullsManualTest.class
}) // @formatter:on
public class PersistenceTestSuite {
    
}
