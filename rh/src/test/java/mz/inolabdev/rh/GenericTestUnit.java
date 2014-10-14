package mz.inolabdev.rh;


import mz.inolabdev.rh.config.PersistenceJPAConfig;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class GenericTestUnit {

	
}
