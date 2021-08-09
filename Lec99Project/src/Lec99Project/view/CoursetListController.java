package Lec99Project.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import Lec99Project.dao.CourseDao;
import Lec99Project.dao.StudentDao;
import Lec99Project.model.CourseVO;
import Lec99Project.model.StudentVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class CoursetListController implements Initializable {
	// 제어가 필요한 컨트롤러 명칭 -> fx:id
	@FXML
	private TextField txtCourseNo;
	@FXML
	private TextField txtStudentNo;
	@FXML
	private TextField txtStudentName;
	@FXML
	private TextField txtSubjectNo;
	@FXML
	private TextField txtSubjectName;
	@FXML
	private TextField txtClassNo;
	@FXML
	private TextField txtPeriod;
	@FXML
	private TextField txtScore;
	@FXML
	private TextField txtGrade;
	@FXML
	private TextField txtCDate;
	@FXML
	private TextField txtSelectNo;

	@FXML
	private Button btnSelect;
	@FXML
	private Button btnAll;
	@FXML
	private Button btnNew;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;

	@FXML
	private TableView<CourseVO> courseTable;
	@FXML
	private TableColumn<CourseVO, String> columnCourseNo;
	@FXML
	private TableColumn<CourseVO, String> columnStudentNo;
	@FXML
	private TableColumn<CourseVO, String> columnStudentName;
	@FXML
	private TableColumn<CourseVO, String> columnSubjectNo;
	@FXML
	private TableColumn<CourseVO, String> columnSubjectName;
	@FXML
	private TableColumn<CourseVO, String> columnClassNo;
	@FXML
	private TableColumn<CourseVO, String> columnPeriod;
	@FXML
	private TableColumn<CourseVO, String> columnScore;
	@FXML
	private TableColumn<CourseVO, String> columnGrade;
	@FXML
	private TableColumn<CourseVO, String> columnCDate;

	private CourseDao dao = null;
	private Mode currentMode = Mode.VIEW_MODE;
	private ObservableList<CourseVO> courseList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		dao = new CourseDao();
		List<CourseVO> t = dao.getCourseList();
		courseList.addAll(t);

		TableColumn<CourseVO, String> columnCourseNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(0);
		TableColumn<CourseVO, String> columnStudentNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(1);
		TableColumn<CourseVO, String> columnStudentName = (TableColumn<CourseVO, String>) courseTable.getColumns().get(2);
		TableColumn<CourseVO, String> columnSubjectNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(3);
		TableColumn<CourseVO, String> columnSubjectName = (TableColumn<CourseVO, String>) courseTable.getColumns().get(4);
		TableColumn<CourseVO, String> columnClassNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(5);
		TableColumn<CourseVO, String> columnPeriod = (TableColumn<CourseVO, String>) courseTable.getColumns().get(6);
		TableColumn<CourseVO, String> columnScore = (TableColumn<CourseVO, String>) courseTable.getColumns().get(7);
		TableColumn<CourseVO, String> columnGrade = (TableColumn<CourseVO, String>) courseTable.getColumns().get(8);
		TableColumn<CourseVO, String> columnCDate = (TableColumn<CourseVO, String>) courseTable.getColumns().get(9);
		

		columnCourseNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("courseNo"));
		columnStudentNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("studentNo"));
		columnStudentName.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("studentName"));
		columnSubjectNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("subjectNo"));
		columnSubjectName.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("subjectName"));
		columnClassNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("classNo"));
		columnPeriod.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("period"));
		columnScore.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("score"));
		columnGrade.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("grade"));
		columnCDate.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("cDate"));
		

		courseTable.setItems(courseList);
		courseTable.getSelectionModel().selectedItemProperty().addListener((observer, oldValue, newValue) -> {
			changeInputMode(Mode.VIEW_MODE);
			viewCourse(newValue);
		});
		btnAll.setOnAction(e -> handleBtnAll(e));
		btnSelect.setOnAction(e -> handleBtnSelect(e));
		btnNew.setOnAction(e -> handleBtnNew(e)); 			// 전체조회
		btnEdit.setOnAction(e -> handleBtnEdit(e));
		btnSave.setOnAction(e -> handleBtnSave(e));
		btnCancel.setOnAction(e -> handleBtnCancel(e));
	}
	
	public void setCourseList(String stno) {
		if(stno == null) {
			List<CourseVO> t = dao.getCourseList();
			courseList.clear();
			courseList.addAll(t);
		}else {
			List<CourseVO> t = dao.getCourse(stno);
			courseList.clear();
			courseList.addAll(t);
		}
	}
	
	private void handleBtnAll(ActionEvent e) {
		dao = new CourseDao();
		List<CourseVO> t = dao.getCourseList();
		courseList.clear();
		courseList.addAll(t);

		TableColumn<CourseVO, String> columnCourseNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(0);
		TableColumn<CourseVO, String> columnStudentNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(1);
		TableColumn<CourseVO, String> columnStudentName = (TableColumn<CourseVO, String>) courseTable.getColumns().get(2);
		TableColumn<CourseVO, String> columnSubjectNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(3);
		TableColumn<CourseVO, String> columnSubjectName = (TableColumn<CourseVO, String>) courseTable.getColumns().get(4);
		TableColumn<CourseVO, String> columnClassNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(5);
		TableColumn<CourseVO, String> columnPeriod = (TableColumn<CourseVO, String>) courseTable.getColumns().get(6);
		TableColumn<CourseVO, String> columnScore = (TableColumn<CourseVO, String>) courseTable.getColumns().get(7);
		TableColumn<CourseVO, String> columnGrade = (TableColumn<CourseVO, String>) courseTable.getColumns().get(8);
		TableColumn<CourseVO, String> columnCDate = (TableColumn<CourseVO, String>) courseTable.getColumns().get(9);
		

		columnCourseNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("courseNo"));
		columnStudentNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("studentNo"));
		columnStudentName.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("studentName"));
		columnSubjectNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("subjectNo"));
		columnSubjectName.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("subjectName"));
		columnClassNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("classNo"));
		columnPeriod.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("period"));
		columnScore.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("score"));
		columnGrade.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("grade"));
		columnCDate.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("cDate"));
		

		courseTable.setItems(courseList);
		courseTable.getSelectionModel().selectedItemProperty().addListener((observer, oldValue, newValue) -> {
			changeInputMode(Mode.VIEW_MODE);
			viewCourse(newValue);
		});
	}
	
	private void handleBtnSelect(ActionEvent e) {
		dao = new CourseDao();
		setCourseList(txtSelectNo.getText());
		
		TableColumn<CourseVO, String> columnCourseNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(0);
		TableColumn<CourseVO, String> columnStudentNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(1);
		TableColumn<CourseVO, String> columnStudentName = (TableColumn<CourseVO, String>) courseTable.getColumns().get(2);
		TableColumn<CourseVO, String> columnSubjectNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(3);
		TableColumn<CourseVO, String> columnSubjectName = (TableColumn<CourseVO, String>) courseTable.getColumns().get(4);
		TableColumn<CourseVO, String> columnClassNo = (TableColumn<CourseVO, String>) courseTable.getColumns().get(5);
		TableColumn<CourseVO, String> columnPeriod = (TableColumn<CourseVO, String>) courseTable.getColumns().get(6);
		TableColumn<CourseVO, String> columnScore = (TableColumn<CourseVO, String>) courseTable.getColumns().get(7);
		TableColumn<CourseVO, String> columnGrade = (TableColumn<CourseVO, String>) courseTable.getColumns().get(8);
		TableColumn<CourseVO, String> columnCDate = (TableColumn<CourseVO, String>) courseTable.getColumns().get(9);
		

		columnCourseNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("courseNo"));
		columnStudentNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("studentNo"));
		columnStudentName.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("studentName"));
		columnSubjectNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("subjectNo"));
		columnSubjectName.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("subjectName"));
		columnClassNo.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("classNo"));
		columnPeriod.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("period"));
		columnScore.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("score"));
		columnGrade.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("grade"));
		columnCDate.setCellValueFactory(new PropertyValueFactory<CourseVO, String>("cDate"));
		

		courseTable.setItems(courseList);
		courseTable.getSelectionModel().selectedItemProperty().addListener((observer, oldValue, newValue) -> {
			changeInputMode(Mode.VIEW_MODE);
			viewCourse(newValue);
		});
	}
	
	private void handleBtnNew(ActionEvent e) {
		viewCourse(null);
		currentMode = Mode.NEW_MODE;
		changeInputMode(Mode.NEW_MODE);
		changeButtonMode(true);
	}

	private void handleBtnEdit(ActionEvent e) {
		currentMode = Mode.EDIT_MODE;
		changeInputMode(Mode.EDIT_MODE);
		changeButtonMode(true);
	}

	public static Integer myParseInt(String txt) {
		if(txt == null) return null;
		if(!StringUtils.isNumeric(txt)) return null;
		
		try {
			return Integer.parseInt(txt);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}	
		
	}
	
	public static Double myParseDouble(String txt) {
		if(txt == null) return null;
		if(!StringUtils.isNumeric(txt)) return null;
		
		try {
			return Double.parseDouble(txt);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}	
		
	}
	
	private void handleBtnSave(ActionEvent e) {
		if (!isInputValid()) { // 검증에 실패하면 false 반환
			return;
		}
		CourseVO course = new CourseVO();
		course.setCourseNo(myParseInt( txtCourseNo.getText()));
		course.setStudentNo(txtStudentNo.getText());
		course.setStudentName(txtStudentName.getText());
		course.setSubjectNo(myParseInt( txtSubjectNo.getText()));
		course.setSubjectName(txtSubjectName.getText());
		course.setClassNo(txtClassNo.getText());
		course.setPeriod(myParseInt( txtPeriod.getText()));
		course.setScore(txtScore.getText());
		course.setGrade(myParseInt( txtGrade.getText()));
		course.setcDate(txtCDate.getText());
		
		if (currentMode == Mode.NEW_MODE) {
			// 신규 저장 : DB에 해당 회원이 존재하는지 확인
			// 존재: 해당 아이디가 존재합니다.
			// 존재X : DB에 저장 (insert)
			String no = txtSubjectNo.getText();
			CourseVO m = dao.getCourse1(no);
			if (m != null) {
				msgbox("과목 중복", "해당 과목은 이미 존재합니다. 다른 과목을 입력하세요");
				return;
			}
			dao.insertCourse(course);
			// DB에 저장후 테이블에 모델에 추가
			// memberTable.getSelectionModel().getSelectedItems().add(member); 에러발생
			courseList.add(course);
			msgbox("등록완료", "등록이 완료되었습니다.");
		} else {// 수정 // DB에 저장(update)
			boolean result = dao.updateCourse(course);

			if (result) {
				// DB에 저장후 테이블에 모델에 추가
				CourseVO orig = courseTable.getSelectionModel().getSelectedItem();
				// clone 유틸: 리플렉션을 사용해서 공통화
				// BeanUtils.copyProperties(member, orig); => class간의 property 복사
				// VO : javaFx property 형으로 정의 (기본형으로는 화면과 동기화에 버그)
				orig.setCourseNo(course.getCourseNo());
				orig.setStudentNo(course.getStudentNo());
				orig.setStudentName(course.getStudentName());
				orig.setSubjectNo(course.getSubjectNo());
				orig.setStudentName(course.getSubjectName());
				orig.setClassNo(course.getClassNo());
				orig.setPeriod(course.getPeriod());
				orig.setScore(course.getScore());
				orig.setGrade(course.getGrade());
				orig.setcDate(course.getcDate());
				msgbox("수정완료", "수강정보가 수정되었습니다.");
			} else {
				msgbox("수정실패", "과목번호, 수강번호, 과목이름 또는 학점 확인해주세요 ");
				return;
			}
		}
		changeInputMode(Mode.VIEW_MODE);
		changeButtonMode(false);
	} // handleBtnSave

	private void handleBtnCancel(ActionEvent e) {

		CourseVO currentCourse = courseTable.getSelectionModel().getSelectedItem();
		viewCourse(currentCourse);

		changeInputMode(Mode.VIEW_MODE);
		changeButtonMode(false);
	}

	private void viewCourse(CourseVO course) {
		if (course != null) {
			txtCourseNo.setText(""+course.getCourseNo());
			txtStudentNo.setText(course.getStudentNo());
			txtStudentName.setText(course.getStudentName());
			txtSubjectNo.setText(course.getStudentNo());
			txtSubjectName.setText(course.getSubjectName());
			txtClassNo.setText(course.getClassNo());
			txtPeriod.setText(""+course.getPeriod());
			txtScore.setText(course.getScore());
			txtGrade.setText(""+course.getGrade());
			txtCDate.setText(course.getcDate());
		} else {
			txtCourseNo.setText("");
			txtStudentNo.setText("");
			txtStudentName.setText("");
			txtSubjectNo.setText("");
			txtSubjectName.setText("");
			txtClassNo.setText("");
			txtPeriod.setText("");
			txtScore.setText("");
			txtGrade.setText("");
			txtCDate.setText("");
		}

	}

	private void changeInputMode(Mode changeMode) {
		txtCourseNo.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtStudentNo.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtStudentName.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtSubjectNo.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtSubjectName.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtClassNo.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtPeriod.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtScore.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtGrade.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtCDate.setEditable(changeMode == Mode.VIEW_MODE ? false : true);

		if (changeMode == Mode.NEW_MODE) {
			txtCourseNo.setEditable(true);
		} else {
			txtCourseNo.setEditable(false);
		}
	}

	public void changeButtonMode(boolean modify) {
		// modify : false 신규, 변경 활성화 | true 저장, 취소 활성화
		btnNew.setDisable(modify);
		btnEdit.setDisable(modify);
		btnSave.setDisable(!modify);
		btnCancel.setDisable(!modify);
	}
	
	private boolean isInputValid() {
        String errorMessage = "";

        if (StringUtils.isBlank(txtCourseNo.getText())) {
            errorMessage += "수강내역번호는 필수입니다.\n";
        }
        if (txtCourseNo.getText() != null && !txtCourseNo.getText().matches("^\\d{1,3}$")) {
            errorMessage += "수강내역번호는 숫자로 1~3글자입니다.\n";
        }
        if (StringUtils.isBlank(txtSubjectNo.getText())) {
            errorMessage += "과목번호는 필수입니다.\n";
        }
        if (txtSubjectNo.getText() != null && !txtSubjectNo.getText().matches("^\\d{3}$")) {
            errorMessage += "과목번호는 숫자로 3글자입니다.\n";
        }
        if (StringUtils.isBlank(txtStudentName.getText())) {
            errorMessage += "과목이름은 필수입니다.\n";
        }
        if (StringUtils.isBlank(txtGrade.getText())) {
            errorMessage += "학점은 필수입니다.\n";
        }
        if (StringUtils.isBlank(txtStudentNo.getText())) {
            errorMessage += "학번은 필수입니다.\n";
        }
        if (StringUtils.isBlank(txtScore.getText())) {
            errorMessage += "성적입력은 필수입니다.\n";
        }
        
        // 마일리지 : 입력시 숫자 
        // 생일 : 입력시 날짜형식 
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // 오류 메시지를 보여준다.
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("유효성 검증 실패");
//            alert.setHeaderText("아래의 내용을 확인해 주세요");
//            alert.setContentText(errorMessage);
//            alert.showAndWait();
        	msgbox("유효성 검증 실패" ,"아래의 내용을 확인해 주세요", errorMessage, AlertType.ERROR);
            return false;
        }
    }
	
	private void msgbox(String title, String content) {
		msgbox(title, null, content, AlertType.INFORMATION);
	}

	private void msgbox(String title, String header, String content) {
		msgbox(title, header, content, AlertType.INFORMATION);
	}

	private void msgbox(String title, String header, String content, AlertType alertType) {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
	}

}

