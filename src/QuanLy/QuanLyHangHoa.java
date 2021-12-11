package QuanLy;

import java.io.IOException;
import java.util.Scanner;

public class QuanLyHangHoa {
	public static Scanner sc = new Scanner(System.in);
	public static Kho wrhouse = new Kho();

	public static int Menu() {

		System.out.println("1. Thêm/ Xóa/ Sửa hàng hóa!");
		System.out.println("2. Tìm kiếm hàng hóa!");
		System.out.println("3. Sắp xếp danh sách!");
		System.out.println("4. Thống kê hàng hóa!");
		System.out.println("5. In toàn bộ!");
		System.out.println("6. Lưu dữ liệu và thoát!");
		System.out.println("Lựa chọn: ");
		while (true) {
			try {
				int i = Integer.parseInt(sc.nextLine());
				if (i < 1 || i > 6) {
					System.out.println("Nhập lại, chỉ được chọn từ 1 đến 6");
				} else
					return i;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Nhập lại, phải là số nguyên từ 1 đến 6!");
			}
		}
	}

	public static void Option1() {
		System.out.println("1. Thêm hàng hóa!");
		System.out.println("2. Sửa hàng hóa!");
		System.out.println("3. Xóa hàng hóa!");
		System.out.println("4. Quay lại!");
		System.out.println("Lựa chọn: ");
		int i = 0;
		while (true) {
			try {
				i = Integer.parseInt(sc.nextLine());
				if (i < 1 || i > 4) {
					System.out.println("Nhập lại, chỉ được chọn từ 1 đến 4");
				} else
					break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Nhập lại, phải là số nguyên từ 1 đến 4!");
			}
		}
		switch (i) {
		case 1: {
			wrhouse.addHangHoa();
			break;
		}
		case 2: {
			wrhouse.uptHangHoa();
			break;
		}
		case 3: {
			wrhouse.delHangHoa();
			break;
		}
		case 4: {
			break;
		}
		default:

			break;
		}
	}

	public static void Option2() {
		System.out.println("1. Tìm kiếm theo loại!");
		System.out.println("2. Tìm kiếm theo giá!");
		System.out.println("3. Tìm kiếm theo khoảng giá!");
		System.out.println("4. Tìm kiếm theo ngày!");
		System.out.println("5. Tìm kiếm theo khoảng ngày!");
		System.out.println("6. Quay lại!");
		System.out.println("Lựa chọn: ");
		int i = 0;
		while (true) {
			try {
				i = Integer.parseInt(sc.nextLine());
				if (i < 1 || i > 6) {
					System.out.println("Nhập lại, chỉ được chọn từ 1 đến 6");
				} else
					break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Nhập lại, phải là số nguyên từ 1 đến 6!");
			}
		}
		switch (i) {
		case 1: {
			wrhouse.findByType();
			;
			break;
		}
		case 2: {
			wrhouse.findByCost();
			break;
		}
		case 3: {
			wrhouse.findByCostFromTo();
			break;
		}
		case 4: {
			wrhouse.findByDate();
			break;
		}
		case 5: {
			wrhouse.findByDateFromTo();
			break;
		}
		case 6: {
			break;
		}
		default:

			break;
		}
	}

	public static void Option3() {
		System.out.println("1. Sắp xếp theo giá nhập!");
		System.out.println("2. Sắp xếp theo ngày nhập!");
		System.out.println("3. Sắp xếp theo loại và theo ngày nhập!");
		System.out.println("4. Sắp xếp theo loại và theo giá!");
		System.out.println("5. Quay lại!");
		System.out.println("Lựa chọn: ");
		int i = 0;
		while (true) {
			try {
				i = Integer.parseInt(sc.nextLine());
				if (i < 1 || i > 5) {
					System.out.println("Nhập lại, chỉ được chọn từ 1 đến 5");
				} else
					break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Nhập lại, phải là số nguyên từ 1 đến 5!");
			}
		}
		switch (i) {
		case 1: {
			System.out.println("Tăng dần (1)/ Giảm dần (2)");
			int k = sc.nextInt();
			if (k == 1)
				wrhouse.sortByCost(true);
			else
				wrhouse.sortByCost(false);
			break;
		}
		case 2: {
			System.out.println("Tăng dần (1)/ Giảm dần (2)");
			int k = sc.nextInt();
			if (k == 1)
				wrhouse.sortByDate(true);
			else
				wrhouse.sortByDate(false);
			break;
		}
		case 3: {
			wrhouse.sortByTypeAndDate();
			break;
		}
		case 4: {
			wrhouse.sortByTypeAndCost();
			break;
		}
		default:

			break;
		}
	}

	public static void Option4() {
		wrhouse.statistical();
	}

	public static void Option5() {
		Kho.print(wrhouse.getList());
	}

	public static void Option6() throws IOException {
		wrhouse.saveToFile("KhoHang.txt");
	}

	public static void run() throws IOException {
		wrhouse.inputFromFile("KhoHang.txt");
		while (true) {
			switch (Menu()) {
			case 1: {
				Option1();
				break;
			}
			case 2: {
				Option2();
				break;
			}
			case 3: {
				Option3();
				break;
			}
			case 4: {
				Option4();
				break;
			}
			case 5: {
				Option5();
				break;
			}
			case 6: {
				Option6();
				return;
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + Menu());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		run();
	}

}
