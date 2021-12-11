package QuanLy;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Comparator;
import java.time.temporal.ChronoUnit;

public class Kho {
	LinkedList<HangHoa> list;

	public Kho() {
		list = new LinkedList<HangHoa>();
		// TODO Auto-generated constructor stub
	}

	public LinkedList<HangHoa> getList() {
		return list;
	}

	public void setList(LinkedList<HangHoa> list) {
		this.list = list;
	}
	
	public static void print(LinkedList<HangHoa> list) {
		System.out.println("===========================================================");
		for (HangHoa hangHoa : list) {
			System.out.println(hangHoa.print());
		}
		System.out.println("===========================================================");
	}
	
	public HangHoa getHangHoaByID(String maHangHoa) {
		HangHoa hh = null;
		for (HangHoa hangHoa : list) {
			if(hangHoa.getMaHangHoa().equals(maHangHoa)) {
				hh = new HangHoa();
				hh = hangHoa;
			}
		}
		return hh;
	}
	
	public boolean add(HangHoa hh) {
		boolean result = false;
		HangHoa checkExist = getHangHoaByID(hh.maHangHoa);
		if (checkExist == null) {
			list.add(hh);
			result = true;
		}
		return result;
	}
	
	public void addHangHoa() {
		HangHoa hh  = new HangHoa();
		hh.Input();
		if(this.add(hh)) System.out.println("Thêm hàng "+ hh.getMaHangHoa()+" thành công!");
		else System.out.println("Thêm hàng hóa "+ hh.getMaHangHoa()+" thất bại! Mã đã tồn tại!");
	}
	
	public boolean del(String maHangHoa) {
		boolean result = false;
		HangHoa checkExist = getHangHoaByID(maHangHoa);
		if (checkExist != null) {
			list.remove(checkExist);
			result = true;
		}
		return result;
	}
	
	public void delHangHoa() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mã hàng muốn xóa: ");
		String ma = sc.nextLine();
		if(this.del(ma)) System.out.println("Xóa hàng "+ ma+" thành công!");
		else System.out.println("Xóa thất bại, mã hàng không tồn tại!");
	}
	
	public boolean update(String maHangHoa) {
		boolean result = false;
		HangHoa checkExist = getHangHoaByID(maHangHoa);
		if (checkExist != null) {
			checkExist.Edit();
			result = true;
		}
		return result;
	}
	
	public void uptHangHoa() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mã hàng muốn sửa: ");
		String ma = sc.nextLine();
		if(this.update(ma)) System.out.println("Thay đổi hàng "+ ma+" thành công!");
		else System.out.println("Chỉnh sửa thất bại, mã hàng không tồn tại!");
	}
	
	public void findByType() {
		
		LinkedList<HangHoa> li = new LinkedList<HangHoa>();
		String type = HangHoa.nhapLoaiHangHoa();
		for (HangHoa hangHoa : list) {
			if (hangHoa.loai.compareTo(type) ==0) li.add(hangHoa);
		}
		Kho.print(li);
	}
	
	public void findByCost() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập giá muốn tìm kiếm: ");
		double cost = sc.nextDouble();
		LinkedList<HangHoa> li = new LinkedList<HangHoa>();
		for (HangHoa hangHoa : list) {
			if(hangHoa.getGiaNhap() == cost) li.add(hangHoa);
		}
		Kho.print(li);
	}
	
	public void findByCostFromTo() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập giá muốn tìm kiếm từ: ");
		double costLeft = sc.nextDouble();
		System.out.print("Đến: ");
		double costRight = sc.nextDouble();
		LinkedList<HangHoa> li = new LinkedList<HangHoa>();
		for (HangHoa hangHoa : list) {
			if(hangHoa.getGiaNhap() >= costLeft && hangHoa.getGiaNhap() <= costRight) li.add(hangHoa);
		}
		Kho.print(li);
	}
	
	public void findByDate() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập ngày muốn tìm kiếm (dd-MM-yyyy): ");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date;
		try {
			date = LocalDate.parse(sc.nextLine(), dateFormat);
		}catch (Exception e) {
			System.out.println("Ngày nhập không đúng định dạng!");
			return;
		}
		LinkedList<HangHoa> li = new LinkedList<HangHoa>();
		for (HangHoa hangHoa : list) {
			if(hangHoa.getNgayNhapKho().isEqual(date)) li.add(hangHoa);
		}
		Kho.print(li);
	}
	
	public void findByDateFromTo() {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		System.out.print("Nhập ngày bắt đầu (dd-MM-yyyy): ");
		LocalDate dateFrom;
		try {
			dateFrom = LocalDate.parse(sc.nextLine(), dateFormat);
		}catch (Exception e) {
			System.out.println("Ngày nhập không đúng định dạng!");
			return;
		}
		System.out.print("Nhập ngày kết thúc (dd-MM-yyyy): ");
		LocalDate dateTo;
		try {
			dateTo = LocalDate.parse(sc.nextLine(), dateFormat);
		}catch (Exception e) {
			System.out.println("Ngày nhập không đúng định dạng!");
			return;
		}
		LinkedList<HangHoa> li = new LinkedList<HangHoa>();
		for (HangHoa hangHoa : list) {
			if(hangHoa.getNgayNhapKho().isAfter(dateFrom) && hangHoa.getNgayNhapKho().isBefore(dateTo)) li.add(hangHoa);
		}
		Kho.print(li);
	}
	
	public void sortByDate(boolean isUp) {
		//this.list.sort(Comparator.comparing(HangHoa::getNgayNhapKho));
		Collections.sort(this.list, new Comparator<HangHoa>() {
				@Override public int compare(HangHoa s1, HangHoa s2) 
				{ 
					if (isUp) return -(int)ChronoUnit.DAYS.between(s1.getNgayNhapKho(), s2.getNgayNhapKho());
					else return (int)ChronoUnit.DAYS.between(s1.getNgayNhapKho(), s2.getNgayNhapKho());} } );
		
	}
	public void sortByCost(boolean isUp) {
		Collections.sort(this.list, new Comparator<HangHoa>() {
			@Override public int compare(HangHoa s1, HangHoa s2) 
			{ 
				if (isUp) return (int)((s1.getGiaNhap() - s2.getGiaNhap())*10000);
				else return (int)((s2.getGiaNhap() - s1.getGiaNhap())*10000);} } );
	}
	 
	public void sortByTypeAndCost() {
		this.list.sort(Comparator.comparing(HangHoa::getLoai).thenComparing(HangHoa::getGiaNhap));
	}
	
	public void sortByTypeAndDate() {
		this.list.sort(Comparator.comparing(HangHoa::getLoai).thenComparing(HangHoa::getNgayNhapKho));
	}
	
	public void statistical() {
		int totalQuantity =0;
		double totalCost =0;
		int[] totalPerType = new int[3];
		String[] type = new String[3];
		type[0] = "THUCPHAM";
		type[1] = "SANHSU";
		type[2] = "DIENMAY";
		for(int i=0;i<totalPerType.length;i++) {
			totalPerType[i] =0;
		}
		for (HangHoa hangHoa : list) {
			totalQuantity+=hangHoa.getSoLuong();
			totalCost+=hangHoa.getSoLuong()*hangHoa.getGiaNhap();
			
			for (int i=0;i<3;i++) {
				if (hangHoa.getLoai().compareTo(type[i]) ==0) totalPerType[i]++;
			}
		}
		System.out.println("Tổng số lượng hàng hóa: "+ totalQuantity);
		System.out.println("Tổng giá trị nhập kho: "+ totalCost);
		System.out.println("Số lượng từng loại hàng");
		for(int i=0;i<3;i++) {
			System.out.println("\t"+type[i]+ ": "+totalPerType[i]+" hàng");
		}
	}
	
	public void inputFromFile(String fileLocation) throws IOException {
		File file = new File(fileLocation);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
 
        String line = "";
        while((line = reader.readLine()) != null){
            System.out.println(line);
            this.add(new HangHoa(line));
        }
	}
	
	public void saveToFile(String fileLocation) throws IOException {
		File file = new File(fileLocation);
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for (HangHoa hangHoa : list) {
        	outputStreamWriter.write(hangHoa.toString());
            // Dùng để xuống hàng
            outputStreamWriter.write("\n");
		}
        outputStreamWriter.flush();
        System.out.println("Lưu dữ liệu thành công!");
	}
}
