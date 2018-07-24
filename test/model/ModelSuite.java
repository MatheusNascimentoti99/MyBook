/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Matheus Nascimento
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({model.AmizadeTest.class, model.UserTest.class, model.PostagemTest.class})
public class ModelSuite {

    @Before
    public void setUp() throws Exception {
    }
    
}
