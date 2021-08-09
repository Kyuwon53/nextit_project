package Lec99Project.view;

import java.awt.MenuBar;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import Lec99Project.dao.StudentDao;
import Lec99Project.model.StudentVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentListController implements Initializable {
	// 제어가 필요한 컨트롤러 명칭 -> fx:id
	@FXML
	private TextField txtStudentNo;
	@FXML
	private TextField txtStudentName;
	@FXML
	private TextField txtStudentAddr;
	@FXML
	private TextField txtMajor;
	@FXML
	private TextField txtMinor;
	@FXML
	private TextField txtBirth;
	@FXML
	private TextField txtSemester;
	@FXML
	private TextField txtGrade;

	@FXML
	private Button btnNew;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	
	@FXML
	private MenuButton menuBtn; 
	
	@FXML 
	private MenuItem menuStudent;
	@FXML 
	private MenuItem menuCourse;
	@FXML 
	private MenuItem menuSubject;

	@FXML
	private TableView<StudentVO> studentTable;
	@FXML
	private TableColumn<StudentVO, String> columnStudentNo;
	@FXML
	private TableColumn<StudentVO, String> columnStudentName;
	@FXML
	private TableColumn<StudentVO, String> columnMajor;

	private StudentDao dao = null;
	private Mode currentMode = Mode.VIEW_MODE;
	private ObservableList<StudentVO> studentList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new StudentDao();
		List<StudentVO> t = dao.getStudentList();
		System.out.println(t);
		studentList.addAll(t);

		TableColumn<StudentVO, String> columnStudentNo = (TableColumn<StudentVO, String>) studentTable.getColumns()
				.get(0);
		TableColumn<StudentVO, String> columnStudentName = (TableColumn<StudentVO, String>) studentTable.getColumns()
				.get(1);
		TableColumn<StudentVO, String> columnMajor = (TableColumn<StudentVO, String>) studentTable.getColumns().get(2);

		columnStudentNo.setCellValueFactory(new PropertyValueFactory<StudentVO, String>("studentNo"));
		columnStudentName.setCellValueFactory(new PropertyValueFactory<StudentVO, String>("studentName"));
		columnMajor.setCellValueFactory(new PropertyValueFactory<StudentVO, String>("major"));

		studentTable.setItems(studentList);
		studentTable.getSelectionModel().selectedItemProperty().addListener((observer, oldValue, newValue) -> {
			changeInputMode(Mode.VIEW_MODE);
			viewStudent(newValue);
		});

		btnNew.setOnAction(e -> handleBtnNew(e));
		btnEdit.setOnAction(e -> handleBtnEdit(e));
		btnSave.setOnAction(e -> handleBtnSave(e));
		btnCancel.setOnAction(e -> handleBtnCancel(e));
	}

	private void handleBtnNew(ActionEvent e) {
		viewStudent(null);
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
		StudentVO student = new StudentVO();
		student.setStudentNo(txtStudentNo.getText());
		student.setStudentName(txtStudentName.getText());
		student.setStudentaddr(txtStudentAddr.getText());
		student.setMajor(txtMajor.getText());
		student.setMinor(txtMinor.getText());
		student.setBirth(txtBirth.getText());
		student.setSemester(myParseInt(txtSemester.getText()));
		student.setGrade(myParseDouble(txtGrade.getText()));

		if (currentMode == Mode.NEW_MODE) {
			// 신규 저장 : DB에 해당 회원이 존재하는지 확인
			// 존재: 해당 아이디가 존재합니다.
			// 존재X : DB에 저장 (insert)
			String no = txtStudentNo.getText();
			StudentVO m = dao.getStudent(no);
			if (m != null) {
				msgbox("학번 중복", "해당 학번은 이미 사용중입니다. 다른 학번을 사용하세요");
				return;
			}
			dao.insertStudent(student);
			// DB에 저장후 테이블에 모델에 추가
			// memberTable.getSelectionModel().getSelectedItems().add(member); 에러발생
			studentList.add(student);
			msgbox("등록완료", "등록이 완료되었습니다.");
		} else {// 수정 // DB에 저장(update)
			boolean result = dao.updateStudent(student);

			if (result) {
				// DB에 저장후 테이블에 모델에 추가
				StudentVO orig = studentTable.getSelectionModel().getSelectedItem();
				// clone 유틸: 리플렉션을 사용해서 공통화
				// BeanUtils.copyProperties(member, orig); => class간의 property 복사
				// VO : javaFx property 형으로 정의 (기본형으로는 화면과 동기화에 버그)
				orig.setStudentNo(student.getStudentNo());
				orig.setStudentName(student.getStudentName());
				orig.setStudentaddr(student.getStudentaddr());
				orig.setMajor(student.getMajor());
				orig.setMinor(student.getMinor());
				orig.setBirth(student.getBirth());
				orig.setSemester(student.getSemester());
				orig.setGrade(student.getGrade());
				msgbox("수정완료", "학생정보가 수정되었습니다.");
			} else {
				msgbox("수정실패", "학번 또는 비밀번호를 확인해주세요 ");
				return;
			}
		}
		changeInputMode(Mode.VIEW_MODE);
		changeButtonMode(false);
	} // handleBtnSave

	private void handleBtnCancel(ActionEvent e) {

		StudentVO currentStudent = studentTable.getSelectionModel().getSelectedItem();
		viewStudent(currentStudent);

		changeInputMode(Mode.VIEW_MODE);
		changeButtonMode(false);
	}

	private void viewStudent(StudentVO student) {
		if (student != null) {
			txtStudentNo.setText(student.getStudentNo());
			txtStudentName.setText(student.getStudentName());
			txtStudentAddr.setText(student.getStudentaddr());
			txtMajor.setText(student.getMajor());
			txtMinor.setText(student.getMinor());
			txtBirth.setText(student.getBirth());
			txtSemester.setText(""+student.getSemester());
			txtGrade.setText("" + student.getGrade());
		} else {
			txtStudentNo.setText("");
			txtStudentName.setText("");
			txtStudentAddr.setText("");
			txtMajor.setText("");
			txtMinor.setText("");
			txtBirth.setText("");
			txtSemester.setText("");
			txtGrade.setText("");
		}

	}

	private void changeInputMode(Mode changeMode) {
		txtStudentName.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtStudentAddr.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtMajor.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtMinor.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtBirth.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtSemester.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtGrade.setEditable(changeMode == Mode.VIEW_MODE ? false : true);

		if (changeMode == Mode.NEW_MODE) {
			txtStudentNo.setEditable(true);
		} else {
			txtStudentNo.setEditable(false);
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

        if (StringUtils.isBlank(txtStudentNo.getText())) {
            errorMessage += "학번은 필수입니다.\n";
        }
        if (txtStudentNo.getText() != null && !txtStudentNo.getText().matches("^\\d{10}$")) {
            errorMessage += "학번은 숫자로 10글자입니다.\n";
        }
        if (StringUtils.isBlank(txtMajor.getText())) {
            errorMessage += "전공은 필수입니다.\n";
        }
        if (txtMajor.getText() != null && !txtMajor.getText().matches("^\\W+$")) {
            errorMessage += "전공은 문자만 입력 가능합니다.\n";
        }
        if (StringUtils.isBlank(txtStudentName.getText())) {
            errorMessage += "학생이름은 필수입니다.\n";
        }
        if (StringUtils.isBlank(txtStudentName.getText())) {
            errorMessage += "학생이름은 필수입니다.\n";
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

enum Mode {
	NEW_MODE, EDIT_MODE, VIEW_MODE, SEARCH_MODE, SELECT_MODE
}
