// package com.tweetapp.api.controllertest;

// import static org.mockito.Mockito.when;
// //import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// //import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// //import java.util.HashMap;
// import java.util.List;
// //import java.util.Map;
// //import java.util.UUID;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.http.MediaType;
// import org.springframework.mock.web.MockHttpServletResponse;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// //import static org.junit.jupiter.api.Assertions.assertThrows;
// //import static org.mockito.Mockito.times;
// //import static org.mockito.Mockito.verify;
// import com.fasterxml.jackson.core.JsonParseException;
// //import com.fasterxml.jackson.core.JsonParser;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.tweetapp.api.controller.TweetAppController;
// import com.tweetapp.api.model.Tweet;
// import com.tweetapp.api.model.User;
// import com.tweetapp.api.model.UserResponse;
// import com.tweetapp.api.service.TweetService;
// import com.tweetapp.api.service.TweetServiceImpl;
// import com.tweetapp.api.service.UserService;
// import com.tweetapp.api.service.UserServiceImpl;

// @ExtendWith(MockitoExtension.class)
// public class TweetAppControllerTests {
// private MockMvc mockMvc;

// @CreatedDate
// private LocalDateTime postDate;

// String requestJson="{\"email\":\"fse@gmail.com\",\"firstName\":\"admin\",\"lastName\":\"admin\",\"password\":\"admin\"}";

// String responseJson = "{\"id\":\"id\",\"username\":\"admin\",\"password\":\"admin\",\"email\":\"fse@gmail.com\",\"firstName\":\"admin\",\"lastName\":\"admin\",\"contactNumber\":null}";

// String loginResponseJson="{\"user\":{\"id\":\"id\",\"username\":\"admin\",\"password\":\"admin\",\"email\":\"fse@gmail.com\",\"firstName\":\"admin\",\"lastName\":\"admin\",\"contactNumber\":null},\"loginStatus\":\"success\"}";

// String resetPasswordResJson="{\"errorMessage\":null,\"successMessage\":\"Password Updated Successfully\"}";

// User user;
// @Mock
// private UserService userServiceMock = new UserServiceImpl(null, null);



// @Mock
// private TweetService tweetServiceMock = new TweetServiceImpl();




// @InjectMocks
// private TweetAppController userController;


// @SuppressWarnings("deprecation")
// @BeforeEach
// public void setup() {
// MockitoAnnotations.initMocks(this);
// this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
// ObjectMapper objectMapper = new ObjectMapper();
// try {
// this.user = objectMapper.readValue(requestJson, User.class);



// } catch (JsonParseException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (JsonMappingException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }

// @Test
// public void registerationTest() throws Exception {



// User user = new User();
// //user.setId("id");
// user.setEmail("fse@gmail.com");
// user.setFirstName("admin");
// user.setLastName("admin");
// user.setPassword("admin");
// user.setUsername("admin");



// String requestJson = "{\"id\":\"id\",\"username\":\"admin\",\"password\":\"admin\",\"email\":\"fse@gmail.com\",\"firstName\":\"admin\",\"lastName\":\"admin\",\"contactNumber\":null}";





// String url = "/api/v1.0/tweets/register";



// when(userServiceMock.createUser(Mockito.any())).thenReturn(user);
// MockHttpServletResponse response = mockMvc
// .perform(post(url).accept(MediaType.APPLICATION_JSON).content(requestJson)
// .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
// .andReturn().getResponse();



// assertEquals(responseJson, response.getContentAsString());



// }

// @Test
// public void registerNegativeTest() throws Exception {



// String url = "/api/v1.0/tweets/register";



// when(userServiceMock.createUser(Mockito.any())).thenReturn(null);
// MockHttpServletResponse response = mockMvc
// .perform(post(url).accept(MediaType.APPLICATION_JSON).content(requestJson)
// .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
// .andReturn().getResponse();



// assertEquals("", response.getContentAsString());



// }

// @Test
// public void LoginPositiveTest() throws Exception {
// User user = new User();
// //user.setId();
// user.setEmail("fse@gmail.com");
// user.setFirstName("admin");
// user.setLastName("admin");
// user.setPassword("admin");
// user.setUsername("admin");
// String url = "/api/v1.0/tweets/login";
// UserResponse loginResponse1 = new UserResponse();
// loginResponse1.setUser(user);
// loginResponse1.setLoginStatus("success");
// String loginReqJson="{\"username\":\"admin\",\"password\":\"admin\"}";
// when(userServiceMock.loginUser(Mockito.any(),Mockito.any())).thenReturn(loginResponse1);
// MockHttpServletResponse response = mockMvc
// .perform(post(url).accept(MediaType.APPLICATION_JSON).content(loginReqJson)
// .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
// .andReturn().getResponse();



// assertEquals(loginResponseJson, response.getContentAsString());

// }



// @Test
// public void getAllUsersPositiveFlow() throws Exception{
// String url="/api/v1.0/tweets/users/all";
// List<User> user1List= new ArrayList<>();
// User user1 = new User();
// //user1.setId("id");
// user1.setEmail("fse@gmail.com");
// user1.setFirstName("admin");
// user1.setLastName("admin");
// user1.setPassword("admin");
// user1.setUsername("admin");
// user1List.add(user1);

// String expectedJson="[{\"id\":\"id\",\"username\":\"admin\",\"password\":\"admin\",\"email\":\"fse@gmail.com\",\"firstName\":\"admin\",\"lastName\":\"admin\",\"contactNumber\":null}]";

// when(userServiceMock.getAllUsers()).thenReturn(user1List);
// MockHttpServletResponse response = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
// System.out.println(response);

// assertEquals(expectedJson, response.getContentAsString());
// }

// @Test
// public void getUsersTweetsPositiveFlow() throws Exception{
// String url="/api/v1.0/tweets/GK";

// List<Tweet> userTweets1List= new ArrayList<>();
// Tweet userTweets1= new Tweet();
// userTweets1.setId("1");
// userTweets1.setTweetName("Demo Tweet");
// userTweets1.setPostDate(postDate);
// userTweets1.setLikes(190);

// userTweets1List.add(userTweets1);

// String expectedJson="[{\"id\":\"1\",\"tweet\":\"Demo Tweet\",\"postDate\":null,\"likes\":190,\"user\":null,\"replies\":null,\"tweetTag\":null}]";
// when(tweetServiceMock.getAllTweetsByUsername(Mockito.any())).thenReturn(userTweets1List);
// MockHttpServletResponse response = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

// assertEquals(expectedJson, response.getContentAsString());
// }

// @Test
// public void postTweetPositiveTest() throws Exception
// {
// String reqJson="{\"tweet\":\"desc\",\"username\":\"fse@gmail.com\"}";
// String url = "/api/v1.0/tweets/Luffy/add";
// Tweet userTweets1= new Tweet();
// userTweets1.setId("1");
// userTweets1.setTweetName("Demo Tweet");
// userTweets1.setPostDate(postDate);
// userTweets1.setTweetTag("DemoTweetTag");
// userTweets1.setLikes(98);
// userTweets1.setUser(user);
// String responseJson="{\"id\":\"1\",\"tweet\":\"Demo Tweet\",\"postDate\":null,\"likes\":98,\"user\":{\"id\":null,\"username\":null,\"password\":\"admin\",\"email\":\"fse@gmail.com\",\"firstName\":\"admin\",\"lastName\":\"admin\",\"contactNumber\":null},\"replies\":null,\"tweetTag\":\"DemoTweetTag\"}";

// when(tweetServiceMock.postTweetByUsername(Mockito.any(),Mockito.any())).thenReturn(userTweets1);
// MockHttpServletResponse response = mockMvc
// .perform(post(url).accept(MediaType.APPLICATION_JSON).content(reqJson)
// .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
// .andReturn().getResponse();



// assertEquals(responseJson, response.getContentAsString());
// }

// @Test
// public void postReplyPositiveTest() throws Exception
// {
// String reqJson="{\"replyDesc\":\"desc\",\"emailId\":\"fse@gmail.com\",\"tweetId\":\"1\"}";
// String url = "/api/v1.0/tweets/Luffy/reply/625151dbfe9e3d6e778e384b";

// Tweet userTweets12= new Tweet();
// userTweets12.setId("1");
// userTweets12.setTweetName("Demo Tweet");
// userTweets12.setPostDate(postDate);
// userTweets12.setTweetTag("DemoTweetTag");
// userTweets12.setLikes(98);
// userTweets12.setUser(user);
// String responseJson="{\"id\":\"1\",\"tweet\":\"Demo Tweet\",\"postDate\":null,\"likes\":98,\"user\":{\"id\":null,\"username\":null,\"password\":\"admin\",\"email\":\"fse@gmail.com\",\"firstName\":\"admin\",\"lastName\":\"admin\",\"contactNumber\":null},\"replies\":null,\"tweetTag\":\"DemoTweetTag\"}";

// when(tweetServiceMock.replyTweetById(Mockito.any(),Mockito.any())).thenReturn(userTweets12);
// MockHttpServletResponse response = mockMvc
// .perform(post(url).accept(MediaType.APPLICATION_JSON).content(reqJson)
// .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
// .andReturn().getResponse();

// assertEquals(responseJson, response.getContentAsString());
// }

// @Test
// public void getAllTweetsPositiveFlow() throws Exception{
// String url="/api/v1.0/tweets/all";
// List<Tweet> tweetResponse1List= new ArrayList<>();
// Tweet userTweets1= new Tweet();
// userTweets1.setId("1");
// userTweets1.setTweetName("Demo Tweet");
// userTweets1.setPostDate(postDate);
// userTweets1.setTweetTag("DemoTweetTag");
// userTweets1.setLikes(98);
// userTweets1.setUser(user);

// tweetResponse1List.add(userTweets1);

// String expectedJson="[{\"id\":\"1\",\"tweet\":\"Demo Tweet\",\"postDate\":null,\"likes\":98,\"user\":{\"id\":null,\"username\":null,\"password\":\"admin\",\"email\":\"fse@gmail.com\",\"firstName\":\"admin\",\"lastName\":\"admin\",\"contactNumber\":null},\"replies\":null,\"tweetTag\":\"DemoTweetTag\"}]";
// when(tweetServiceMock.getAllTweets()).thenReturn(tweetResponse1List);
// MockHttpServletResponse response = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
// assertEquals(expectedJson, response.getContentAsString());
// }





// }
