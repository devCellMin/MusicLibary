package UserSetting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;



public class UserTableData extends AbstractTableModel{
	List<Userset> UserList;
	String[] headers = new String[] {"아이디","이름","비밀번호","나이","성별","휴대폰번호","거주지", "사용자 유형"};
	String[] showheaders = new String[] {"아이디","이름","나이","성별","사용자 유형"};
	
	public UserTableData() {
		updateList();
	}
	
	private void updateList() {
		UserList = new ArrayList<Userset>();
		try{
			Scanner scanner = new Scanner(new File("database/UserDB.csv"));
			for(int i=0;scanner.hasNextLine();i++) {
				String[] data = scanner.nextLine().split("\",\"");
				if(i!=0) { // CSV의 첫줄은 Header이기 때문이다.
					Userset user = new Userset();
					UserBuilder UB = new UserBuilder(user);
					user = UB.userId(data[0].replace("\"", "")).userName(data[1]).userPWD(data[2])
							.userAge(Integer.parseInt(data[3])).userGender(data[4])
							.userPhoneNum(data[5]).userAddr(data[6]).userType(data[7].replace("\"", "")).getUser();
					UserList.add(user);
				}
			} scanner.close();
		} catch(Exception e) {e.printStackTrace();}
	}

	public List<Userset> getUserList() {
		return UserList;
	}

	public String[] getHeaders() {
		return headers;
	}
	
	public String[] getShowheaders() {
		return showheaders;
	}

	public String[][] getUserTableData() {
		//{"아이디","이름","나이","성별","사용자 유형"};
		updateList();
		String[][] info = new String[UserList.size()][showheaders.length];
		int cnt=0;
		for(Userset user : UserList) {
			info[cnt][0] = user.getUserId();
			info[cnt][1] = user.getUserName();
			info[cnt][2] = String.valueOf(user.getUserAge());
			info[cnt][3] = user.getUserGender();
			info[cnt][4] = user.getUserType();
			cnt++;
		}
		return info;
	}
	
	public Userset getUser(int number) {
		return UserList.get(number);
	}
	
	
	
	public void save(Userset Newuser) {
		UserList.add(Newuser);
		//Method to Save one Music List at Final
		 File csv = new File("database/UserDB.csv"); 
         BufferedWriter bw = null; // 출력 스트림 생성
         try {
             bw = new BufferedWriter(new FileWriter(csv, true));
             // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다\
             String inputdata="\n";
             inputdata += "\""+Newuser.getUserId()+"\",";
        	 inputdata += "\""+Newuser.getUserName()+"\",";
        	 inputdata += "\""+Newuser.getUserPWD()+"\",";
        	 inputdata += "\""+Newuser.getUserAge()+"\",";
        	 inputdata += "\""+Newuser.getUserGender()+"\",";
        	 inputdata += "\""+Newuser.getUserPhoneNum()+"\",";
        	 inputdata += "\""+Newuser.getUserAddr()+"\",";
        	 inputdata += "\""+Newuser.getUserType()+"\"";
        	 bw.write(inputdata);
         }
         catch (FileNotFoundException e1) {e1.printStackTrace();}
         catch (IOException e1) {e1.printStackTrace();}
         finally {
            try {
                 if (bw != null) {
                     bw.flush(); // 남아있는 데이터까지 보내 준다
                     bw.close(); // 사용한 BufferedWriter를 닫아 준다
                 }
             } catch (IOException e1) {e1.printStackTrace();}
         }
	}
	
	public void saveAll(Userset Editeduser) {
		if(Editeduser != null) {
			UserList.set(searchUser(Editeduser), Editeduser);
		}
		//Method to Save All Music List
		 File csv = new File("database/UserDB.csv"); 
         BufferedWriter bw = null; // 출력 스트림 생성
         try {
             bw = new BufferedWriter(new FileWriter(csv));
             bw.write("\"아이디\",\"이름\",\"비밀번호\",\"나이\",\"성별\",\"휴대폰번호\",\"거주지\",\"사용자유형\"");
             bw.newLine();int cnt=0;
             // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다\
             for(Userset user : UserList) {
            	 String inputdata="";
            	 inputdata += "\""+user.getUserId()+"\",";
            	 inputdata += "\""+user.getUserName()+"\",";
            	 inputdata += "\""+user.getUserPWD()+"\",";
            	 inputdata += "\""+user.getUserAge()+"\",";
            	 inputdata += "\""+user.getUserGender()+"\",";
            	 inputdata += "\""+user.getUserPhoneNum()+"\",";
            	 inputdata += "\""+user.getUserAddr()+"\",";
            	 inputdata += "\""+user.getUserType()+"\"";
            	 bw.write(inputdata);
            	 if(cnt<UserList.size()-1) {bw.newLine();} // 개행
            	 cnt++;
             }
         }
         catch (FileNotFoundException e1) {e1.printStackTrace();}
         catch (IOException e1) {e1.printStackTrace();}
         finally {
             try {
                 if (bw != null) {
                     bw.flush(); // 남아있는 데이터까지 보내 준다
                     bw.close(); // 사용한 BufferedWriter를 닫아 준다
                 }
             } catch (IOException e1) {e1.printStackTrace();}
         }
	}

	private int searchUser(Userset user) {
		int cnt =0;
		for(Userset users : UserList) {
			if(users.getUserId().equals(user.getUserId())) {
				break;
			}cnt++;
		}
		return cnt;
	}
	
	public boolean Sameid (String userID) {
		for(Userset user : UserList) {
			if(user.getUserId().equals(userID)) {
				return true;
			}
		}return false;
	}
	
	public Userset UserLogin(List<String> Userinfo) {
		for(Userset user : UserList) {
			if((Userinfo.get(0).equals(user.getUserId()))&&(Userinfo.get(1).equals(user.getUserPWD()))) {
				JOptionPane.showMessageDialog(null, "로그인 성공");
				return user;
			}else if(Userinfo.get(0).equals(user.getUserId())){
				JOptionPane.showMessageDialog(null, "로그인 실패!\n비밀번호를 확인하여주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
				return null;
			}
		}
		JOptionPane.showMessageDialog(null, "로그인 실패!\n아이디를 확인하여주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
		return null;
	}
	
	public Userset updateUser(Userset user) {
		for(Userset info : UserList) {
			if(user.getUserId().equals(info.getUserId())) {
				return info;
			}
		}return null;
	}
	
	public void deleteUserSet(Userset user) {
		UserList.remove(searchUser(user));
		saveAll(null);
	}
	
	// Count of Rows of User Table
	public int getRowCount() {
		return UserList.size();
	}
	
	// Count of Columns for Administer
	public int getColumnCount() {
		return headers.length;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return UserList.get(rowIndex).getUserId();
		case 1:
			return UserList.get(rowIndex).getUserName();
		case 2:
			return UserList.get(rowIndex).getUserPWD();
		case 3:
			return UserList.get(rowIndex).getUserAge();
		case 4:
			return UserList.get(rowIndex).getUserGender();
		case 5:
			return UserList.get(rowIndex).getUserPhoneNum();
		case 6:
			return UserList.get(rowIndex).getUserAddr();
		} return null;
	}
	public void refresh() {
		updateList();
		super.fireTableDataChanged();
	}
	
}
