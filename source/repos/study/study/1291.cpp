#include <iostream>
#include <iomanip>

using namespace std;

int main() {
	
	int s = 0, e = 0;
	while (true) {
		cin >> s;
		cin >> e;
		if (s < 2 || s > 9 || e < 2 || e > 9) {
			printf("INPUT ERROR!\n");
			continue;
		}
		else break;
	}

	if (s >= e) {
		for (int i = 1; i <= 9; i++){
			for (int j = s; j >= e; j--){
				printf("%d * %d = %2d   ",j , i , j*i );
			}
			cout << '\n';
		}
	}
	if (s < e) {
		for (int i = 1; i <= 9; i++) {
			for (int j = s; j <= e; j++) {
				printf("%d * %d = %2d   ", j, i, j*i);
			}
			cout << '\n';
		}
	}
	return 0;
}