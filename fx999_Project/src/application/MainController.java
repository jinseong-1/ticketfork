package application;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

	// 달력을 표시할 그리드
	@FXML
	private GridPane calendarPane;

	@FXML
	private ImageView ImgPost;
	@FXML
	private TextArea txtEX;
	@FXML
	private Button btnRe, btnBMonth, // 이전달 버튼
			btnToday, // 이번달 버튼
			btnNMonth; // 다음달 버튼
			
	@FXML
	private ListView<String> ListView;
	@FXML
	private TextFlow txtfTitle;

	// 선택 월
	private LocalDate date;
	// 현재의 월
	private LocalDate now;

	
	// 이번달 달력을 표시
	@FXML
	void TodayClick(ActionEvent e) {
		date = LocalDate.now();
		String strYearMonth = (date.getYear()) + "년" + (date.getMonthValue()) + "월";
		btnToday.setText(strYearMonth);
		setButton();
	}

	// 이전달 달력 표시 
	@FXML
	void BMonthClick(ActionEvent e) {
		date = date.minusMonths(1);
		String strYearMonth = (date.getYear()) + "년" + (date.getMonthValue()) + "월";
		btnToday.setText(strYearMonth);
		setButton();
	}
		
	// 다음달 달력 표시
	@FXML
	void NMonthClick(ActionEvent e) {
		date = date.plusMonths(1);
		String strYearMonth = (date.getYear()) + "년" + (date.getMonthValue()) + "월";
		btnToday.setText(strYearMonth);
		setButton();
	}
	
	// 버튼 클릭시 달력 생성 
	private void setButton() {
		calendarPane.getChildren().clear();
		
		
		LocalDate firstDate = date.withDayOfMonth(1);
		System.out.println(firstDate);
		System.out.println(firstDate.getDayOfWeek());
		// sunday == 7 , 토요일 == 6, 월요일 == 1
		int weekDay = firstDate.getDayOfWeek().getValue();
		System.out.println(weekDay);
		// LocalDate lastDate = date.withDayOfMonth(date.lengthOfMonth());
		// System.out.println(lastDate);
		// int lastDay = lastDate.getDayOfMonth();
		// System.out.println(lastDay);
		int lastDay = date.lengthOfMonth();
		if(weekDay == 1) {
			lastDay += 1;
		}else if(weekDay == 2) {
			lastDay += 2;
		}else if(weekDay == 3) {
			lastDay += 3;
		}else if(weekDay == 4) {
			lastDay += 4;
		}else if(weekDay == 5) {
			lastDay += 5;
		}else if(weekDay == 6) {
			lastDay += 6;
		}
		double weekCount = Math.ceil(lastDay / 7.0);
		System.out.println(weekCount);
		
		
		LocalDate day =  date.withDayOfMonth(1);
		int dayCount = 1;
		System.out.println(lastDay );
		btnLabel : for(int i = 1; i <= weekCount; i++) {
			// System.out.println(i);
			for(int j = 0; j < 7; j++) {
				if(dayCount > date.lengthOfMonth()) {
					break btnLabel;
				}
				if(i == 1 && weekDay > j) {
					System.out.print("그리면 안됨");
				}else {
					// System.out.print((i+":"+j)+"그려줌"); 버튼 크려줌
					// 버튼 날짜 생성
					String strDay = (dayCount < 10) ? "0"+dayCount: String.valueOf(dayCount);
					Button btn = new Button(strDay);
					// 버튼 넓이
					btn.setPrefWidth(30);
					calendarPane.add(btn, j, i-1);
					btn.setUserData(day);
					System.out.println(date.equals(now));
					
					// 현재 날짜 이전 버튼 비활성화  
					if((date.getYear() < now.getYear()) || (date.getYear() == now.getYear() && date.getMonthValue() < now.getMonthValue()) || date.equals(now) && dayCount < date.getDayOfMonth()) {
						btn.setDisable(true);
					}
					
					// 버튼 클릭시 예약 처리
					btn.setOnAction(e->{
						reservation(e);
					});
					
					// 일요일 색상 지정
					if(day.getDayOfWeek().getValue() == 7) {
						btn.setStyle("-fx-text-fill:red");
					// 토요일 색상 지정
					}else if(day.getDayOfWeek().getValue() == 6) {
						btn.setStyle("-fx-text-fill:blue");
					}
											
					
					System.out.print(" day : " + day.getDayOfMonth());
					System.out.print(", week : "+day.getDayOfWeek().getValue()+"  ");
					day = day.plusDays(1);
					dayCount++;
				}
			}
			System.out.println();
		}
	}

	// 버튼 클릭시 예약 처리
	private void reservation(ActionEvent e) {
		Button btn = (Button)e.getTarget();
		LocalDate selectDate = (LocalDate)btn.getUserData();
		System.out.println(selectDate);	// 선택한 날짜
		
		date = LocalDate.now();	// 오늘 날짜
		System.out.println(date);	
		
		System.out.println(date.getDayOfMonth()); // 오늘 일자	
		System.out.println(selectDate.getDayOfMonth());	// 선택한 일자 
		
		
		// 예매일 관련 안내 	
		if( (date.getDayOfYear()+7)< selectDate.getDayOfYear() ){
			Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setHeaderText("해당일자는 예약이 불가합니다.");
		    alert.setContentText("예매는 공연일 일주일 전 오후 2시부터 가능합니다.");
		    alert.showAndWait();
			}
	} 
	
	
		
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		date = LocalDate.now();
		now = LocalDate.now();
		// 예매하기 버튼 클릭
		btnRe.setOnAction(e -> {

			try {
				Stage stage = new Stage(StageStyle.UTILITY);
				Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("로그인 화면");
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	
	
	
	
	
	
	
	
	

	/*
	 * 
	 * 	// 월의 첫번째 요일과 마지막 요일을 저장할부분
	YearMonth firstAndLastDay;
	// 첫번째날의 요일
	String strFirstWeek;
	// 마지막날의 요일
	String strLastWeek;
	// 해당 날짜의 칸을 클릭
	String clickDate;
	 * 
	 * private int nYear, nMonth; private int startDay, lastDay, inputDate;
	 * 
	 * Calendar cal = Calendar.getInstance();
	 * 
	 * static String clickDate;
	 * 
	 * public String getClickDate(){ return clickDate; }
	 * 
	 * Button[] btnList;
	 *
		 * // 이전달 버튼 클릭
	 * 
	 * @FXML void BMonthClick(ActionEvent e) { if(nMonth == 0) { nMonth = 11; nYear
	 * -= 1; }else { nMonth -= 1; } changeCalendar(nYear,nMonth);
	 * 
	 * };
	 * 
	 * private void changeCalendar(int nYear, int nMonth) { inputDate=1; for(int
	 * i=0; i< btnList.length;i++) { btnList[i].setDisable(false);
	 * btnList[i].setText(" "); }
	 * 
	 * System.out.println(btnList); System.out.println(btnList.length);
	 * 
	 * // 현재 날짜 btnToday.setText(nYear + "년"+(nMonth+1)+"월"); // 현재 날짜와 달, 1일로 set
	 * cal.set(Calendar.YEAR, nYear); cal.set(Calendar.MONTH, nMonth);
	 * cal.set(Calendar.DATE, 1);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @FXML void NMonthClick() {
	 * 
	 * 
	 * 
	 * };
	 * 
	 * @FXML void TodayClick(ActionEvent e) { today(); };
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * private void today() { Calendar calendar = new
	 * GregorianCalendar(Locale.KOREA); nYear = calendar.get(Calendar.YEAR); nMonth
	 * = calendar.get(Calendar.MONTH);
	 * 
	 * changeCalendar(nYear, nMonth);
	 * 
	 * }
	 * 
	 */

}
