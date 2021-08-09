package Lec99Project.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import Lec99Project.dao.SubjectDao;
import Lec99Project.model.SubjectVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SubjectListController implements Initializable{

	// 제어가 필요한 컨트롤러 명칭 -> fx:id
	@FXML
	private TextField txtSubjectNo;
	@FXML
	private TextField txtSubjectName;
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
	private TableView<SubjectVO> subjectTable;
	@FXML
	private TableColumn<SubjectVO, Integer> columnSubjectNo;
	@FXML
	private TableColumn<SubjectVO, String> columnSubjectName;
	@FXML
	private TableColumn<SubjectVO, Integer> columnGrade;

	private SubjectDao dao = null;
	private Mode currentMode = Mode.VIEW_MODE;
	private ObservableList<SubjectVO> subjectList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new SubjectDao();
		List<SubjectVO> t = dao.getSubjectList();
		System.out.println(t);
		subjectList.addAll(t);

		TableColumn<SubjectVO, Integer> columnSubjectNo = (TableColumn<SubjectVO, Integer>) subjectTable.getColumns().get(0);
		TableColumn<SubjectVO, String> columnSubjectName = (TableColumn<SubjectVO, String>) subjectTable.getColumns().get(1);
		TableColumn<SubjectVO, Integer> columnGrade = (TableColumn<SubjectVO, Integer>) subjectTable.getColumns().get(2);

		columnSubjectNo.setCellValueFactory(new PropertyValueFactory<SubjectVO, Integer>("subjectNo"));
		columnSubjectName.setCellValueFactory(new PropertyValueFactory<SubjectVO, String>("subjectName"));
		columnGrade.setCellValueFactory(new PropertyValueFactory<SubjectVO, Integer>("grade"));

		subjectTable.setItems(subjectList);
		subjectTable.getSelectionModel().selectedItemProperty().addListener((observer, oldValue, newValue) -> {
			changeInputMode(Mode.VIEW_MODE);
			viewSubject(newValue);
		});

		btnNew.setOnAction(e -> handleBtnNew(e));
		btnEdit.setOnAction(e -> handleBtnEdit(e));
		btnSave.setOnAction(e -> handleBtnSave(e));
		btnCancel.setOnAction(e -> handleBtnCancel(e));
	}

	private void handleBtnNew(ActionEvent e) {
		viewSubject(null);
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
		if (txt == null)
			return null;
		if (!StringUtils.isNumeric(txt))
			return null;

		try {
			return Integer.parseInt(txt);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Double myParseDouble(String txt) {
		if (txt == null)
			return null;
		if (!StringUtils.isNumeric(txt))
			return null;

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
		SubjectVO subject = new SubjectVO();
		subject.setSubjectNo(myParseInt(txtSubjectNo.getText()));
		subject.setSubjectName(txtSubjectName.getText());
		subject.setGrade(myParseInt(txtGrade.getText()));

		if (currentMode == Mode.NEW_MODE) {
			// 신규 저장 : DB에 해당 회원이 존재하는지 확인
			// 존재: 해당 아이디가 존재합니다.
			// 존재X : DB에 저장 (insert)
			String no = txtSubjectNo.getText();
			SubjectVO m = dao.getSubject(no);
			if (m != null) {
				msgbox("학번 중복", "해당 학번은 이미 사용중입니다. 다른 학번을 사용하세요");
				return;
			}
			dao.insertSubject(subject);
			// DB에 저장후 테이블에 모델에 추가
			// memberTable.getSelectionModel().getSelectedItems().add(member); 에러발생
			subjectList.add(subject);
			msgbox("등록완료", "등록이 완료되었습니다.");
		} else {// 수정 // DB에 저장(update)
			boolean result = dao.updateSubject(subject);

			if (result) {
				// DB에 저장후 테이블에 모델에 추가
				SubjectVO orig = subjectTable.getSelectionModel().getSelectedItem();
				// clone 유틸: 리플렉션을 사용해서 공통화
				// BeanUtils.copyProperties(member, orig); => class간의 property 복사
				// VO : javaFx property 형으로 정의 (기본형으로는 화면과 동기화에 버그)
				orig.setSubjectNo(subject.getSubjectNo());
				orig.setSubjectName(subject.getSubjectName());
				orig.setGrade(subject.getGrade());
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

		SubjectVO currentSubject = subjectTable.getSelectionModel().getSelectedItem();
		viewSubject(currentSubject);

		changeInputMode(Mode.VIEW_MODE);
		changeButtonMode(false);
	}

	private void viewSubject(SubjectVO subject) {
		if (subject != null) {
			txtSubjectNo.setText(""+subject.getSubjectNo());
			txtSubjectName.setText(subject.getSubjectName());
			txtGrade.setText(""+subject.getGrade());
		} else {
			txtSubjectNo.setText("");
			txtSubjectName.setText("");
			txtGrade.setText("");
		}

	}

	private void changeInputMode(Mode changeMode) {
		txtSubjectNo.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtSubjectName.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtGrade.setEditable(changeMode == Mode.VIEW_MODE ? false : true);

		if (changeMode == Mode.NEW_MODE) {
			txtSubjectNo.setEditable(true);
		} else {
			txtSubjectNo.setEditable(false);
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

		if (StringUtils.isBlank(txtSubjectNo.getText())) {
			errorMessage += "과목번호는 필수입니다.\n";
		}
		if (txtSubjectNo.getText() != null && !txtSubjectNo.getText().matches("^\\d{3}$")) {
			errorMessage += "과목번호는 숫자로 3글자입니다.\n";
		}
		if (StringUtils.isBlank(txtSubjectName.getText())) {
			errorMessage += "과목명은 필수입니다.\n";
		}
		if (StringUtils.isBlank(txtGrade.getText())) {
			errorMessage += "학점은 필수입니다.\n";
		}
		if (txtGrade.getText() != null && !txtGrade.getText().matches("^\\d+$")) {
			errorMessage += "학점은 숫자로 1글자입니다.\n";
		}
		// 마일리지 : 입력시 숫자
		// 생일 : 입력시 날짜형식
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// 오류 메시지를 보여준다.
			// Alert alert = new Alert(AlertType.ERROR);
			// alert.setTitle("유효성 검증 실패");
			// alert.setHeaderText("아래의 내용을 확인해 주세요");
			// alert.setContentText(errorMessage);
			// alert.showAndWait();
			msgbox("유효성 검증 실패", "아래의 내용을 확인해 주세요", errorMessage, AlertType.ERROR);
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
