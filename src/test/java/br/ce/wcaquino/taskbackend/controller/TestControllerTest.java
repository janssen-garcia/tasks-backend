package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;









import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TestControllerTest {
	
	@Mock
	private TaskRepo taskrepo;

	@InjectMocks
	private TaskController tc;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao(){
		Task todo = new Task();
	//	todo.setTask("Teste");
		todo.setDueDate(LocalDate.now());
		try {
			tc.save(todo);
			Assert.fail();
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());
		}
		
	}
	@Test
	public void naoDeveSalvarTarefaSemData(){
		
		Task todo = new Task();
		todo.setTask("Teste");
	//  todo.setDueDate(LocalDate.now());
		try {
			tc.save(todo);
			Assert.fail();
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the due date", e.getMessage());
		}		
		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada(){

		Task todo = new Task();
	   todo.setTask("Teste");
	   todo.setDueDate(LocalDate.of(2010,01,01));
		try {
			tc.save(todo);
			Assert.fail();
		} catch (ValidationException e) {
			Assert.assertEquals("Due date must not be in past", e.getMessage());
		}				
		
	}
	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException{
	

	   Task todo = new Task();
	   todo.setTask("Teste");
	   todo.setDueDate(LocalDate.now());
       tc.save(todo);
				
       Mockito.verify(taskrepo).save(todo);
		
	}
	
	
}
