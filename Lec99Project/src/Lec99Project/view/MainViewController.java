package Lec99Project.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Lec99Project.MainApp;
import Lec99Project.dao.StudentDao;
import Lec99Project.model.StudentVO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainViewController implements Initializable {
	
	@FXML
	private BorderPane bp;

	@FXML
	private AnchorPane ap;
	
	@FXML
	private Button btnStudent;
	@FXML
	private Button btnCourse;
	@FXML
	private Button btnSubject;
	
	@FXML
	private Label loginLabel;
	
	@FXML
	private TextField txtStudentNO;
	@FXML
	private TextField txtPw;
	
	public void Login(ActionEvent event) throws Exception{
		StudentDao dao = new StudentDao();
		StudentVO stu = dao.getStudent(txtStudentNO.getText());
		if(stu == null) {
			loginLabel.setText("Login Failed");
		}else {
			if(txtPw.getText().equals(stu.getBirth().substring(5).replace(".",""))) {
//		if(txtStudentNO.getText().equals("user")&&txtPw.getText().equals("pass")) {
				loginLabel.setText("Login Success");
				Parent root = FXMLLoader.load(MainApp.class.getResource("view/StudentList.fxml"));
				bp.setCenter(root);
			}else {
				loginLabel.setText("Login Failed");
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnStudent.setOnAction(e -> handleBtnStudent(e));
		btnCourse.setOnAction(e -> handleBtnCourse(e));
		btnSubject.setOnAction(e -> handleBtnSubject(e));
	}
	
	private void handleBtnStudent(ActionEvent e) {
		try {
			Parent studentList = FXMLLoader.load(MainApp.class.getResource("view/StudentList.fxml"));
			bp.setCenter(studentList);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void handleBtnCourse(ActionEvent e) {
		try {
			Parent courseList = FXMLLoader.load(MainApp.class.getResource("view/CourseList.fxml"));
			bp.setCenter(courseList);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//loadPage("view/CourseList.fxml");
	}
	private void handleBtnSubject(ActionEvent e) {
		try {
			Parent subjectList = FXMLLoader.load(MainApp.class.getResource("view/SubjectList.fxml"));
			bp.setCenter(subjectList);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//loadPage("view/SubjectList.fxml");
	}
	
	private void loadPage(String page) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(page));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);
	}
}
