package co.develhope.crudTestExercise;

import co.develhope.crudTestExercise.Controllers.StudentController;
import co.develhope.crudTestExercise.Entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class StudentControllerTest {

	@Autowired
	private StudentController studentController;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;


	private Student createAStudent() throws Exception {

		Student student = new Student();
		student.setIsWorking(true);
		student.setName("Alessio");
		student.setSurname("Pollina");
		return createAStudent(student);
	}

	private Student createAStudent(Student student) throws Exception {
		MvcResult result = createAStudentRequest(student);
		Student studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(studentFromResponse).isNotNull();
		assertThat(studentFromResponse.getId()).isNotNull();
		return studentFromResponse;
	}

	private MvcResult createAStudentRequest() throws Exception {
		Student student = new Student();
		student.setIsWorking(true);
		student.setName("Alessio");
		student.setSurname("Pollina");
		return createAStudentRequest(student);
	}

	private MvcResult createAStudentRequest(Student student) throws Exception {
		if (student == null) return null;
		String studentJSON = objectMapper.writeValueAsString(student);
		return this.mockMvc.perform(post("/student")
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}



	private Student getStudentFromId(Long id)throws Exception {
		MvcResult result = this.mockMvc.perform(get("/student/" + id))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		try {
			String studentJSON = result.getResponse().getContentAsString();
			Student student = objectMapper.readValue(studentJSON, Student.class);
			assertThat(student).isNotNull();
			assertThat(student.getId()).isNotNull();
			return student;

		} catch (Exception e) {
			return null;
		}
	}

	@Test
	void studentControllerLoads() {
		assertThat(studentController).isNotNull();
	}




	@Test
	void createAStudentTest() throws Exception {
		Student studentFromResponse = createAStudent();
		assertThat(studentFromResponse.getId()).isNotNull();
	}


	@Test
	void readStudentList() throws Exception {
		createAStudentRequest();
		MvcResult result = this.mockMvc.perform(get("/student/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		List<Student> studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
		System.out.println("Student in Database are: " + studentFromResponse.size());
		assertThat(studentFromResponse.size()).isNotZero();
	}

	@Test
	void readSingleStudent() throws Exception {
		Student student = createAStudent();
		Student studentFromResponse = getStudentFromId(student.getId());
		assertThat(studentFromResponse).isNotNull();
		assertThat(studentFromResponse.getId()).isEqualTo(student.getId());
	}

	@Test
	void updateStudent() throws Exception {
		Student student = createAStudent();
		String newName = "Fabrizio";
		student.setName(newName);
		String studentJSON = objectMapper.writeValueAsString(student);
		MvcResult result = this.mockMvc.perform(post("/student")
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		//Check student from Put
		Student studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(studentFromResponse.getId()).isEqualTo(student.getId());
		assertThat(studentFromResponse.getName()).isEqualTo(newName);


		//I get student with Get
		Student studentFromResponseGet = getStudentFromId(student.getId());
		assertThat(studentFromResponseGet.getId()).isEqualTo(student.getId());
		assertThat(studentFromResponseGet.getName()).isEqualTo(newName);

	}

	@Test
	void deleteStudent()throws Exception{
		Student student = createAStudent();
		assertThat(student.getId()).isNotNull();
		this.mockMvc.perform(delete("/student/" + student.getId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();


		Student studentFromResponseGet = getStudentFromId(student.getId());
		assertThat(studentFromResponseGet).isNull();

	}


	@Test
	void workingStudent()throws Exception{
		Student student = createAStudent();
		assertThat(student.getId()).isNotNull();

		MvcResult result = this.mockMvc.perform(put("/student/"+student.getId()+"/working?working=true"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Student studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);

		assertThat(studentFromResponse).isNotNull();
		assertThat(studentFromResponse.getId()).isEqualTo(student.getId());
		assertThat(studentFromResponse.getIsWorking()).isEqualTo(true);

		Student studentFromResponseGet = getStudentFromId(student.getId());

		assertThat(studentFromResponseGet).isNotNull();
		assertThat(studentFromResponseGet.getId()).isEqualTo(student.getId());
		assertThat(studentFromResponseGet.getIsWorking()).isEqualTo(true);

	}
}
