package com.rheiley.taskmanagement;

import com.rheiley.taskmanagement.controller.TaskController;
import com.rheiley.taskmanagement.dto.TaskDto;
import com.rheiley.taskmanagement.entity.Task;
import com.rheiley.taskmanagement.mapper.TaskMapper;
import com.rheiley.taskmanagement.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

@WebMvcTest(TaskController.class)
public class TaskManagementApplicationTests {
	private static final String END_POINT_PATH = "/api/tasks";
	private Task task;
	private TaskDto taskDto;
	private Long taskId;
	private String requestURL;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private TaskService taskService;

	@BeforeEach
	void setUp(){
		task = new Task(1L, "Clean room", "Clean room by 5pm", false);
	}

	@Test
	public void createTaskShouldReturnStatusCode201() throws Exception{
		taskDto = TaskMapper.mapToTaskDto(task);

		Mockito.when(taskService.createTask(taskDto)).thenReturn(taskDto);

		String requestBody = objectMapper.writeValueAsString(taskDto);

		mockMvc.perform(post(END_POINT_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isCreated());
	}

	@Test
	public void getTaskByIdShouldReturnStatusCode200() throws Exception{
		taskId = 1L;

		requestURL = END_POINT_PATH +  "/" + taskId;

		taskDto = TaskMapper.mapToTaskDto(task);

		Mockito.when(taskService.getTaskById(taskId)).thenReturn(taskDto);

		mockMvc.perform(get(requestURL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.taskName", is("Clean room")))
				.andExpect(jsonPath("$.description", is("Clean room by 5pm")))
				.andExpect(jsonPath("$.completed", is(false)));
	}

}
