class ParkingSystem {
public:
    int arr[3];
    ParkingSystem(int big, int medium, int small) {
        arr[0] = big;
        arr[1] = medium;
        arr[2] = small;
    }
    
    bool addCar(int carType) {
        if((arr[carType -1]--)>0)
            return true;
        
        return false;
    }
};