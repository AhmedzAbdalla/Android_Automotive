package com.example.foodplanner.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

// Entity class for the products table in Room
@Entity(tableName = "FavProducts")
public class POJO_class implements Parcelable{
    @PrimaryKey
    @NonNull
    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;

    public POJO_class() {
    }
    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    private String strYoutube;

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public void setStrImageSource(String strImageSource) {
        this.strImageSource = strImageSource;
    }

    public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;
    private String strSource;
    private String strImageSource;
    private String strCreativeCommonsConfirmed;
    private String dateModified;

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }
    public String getStrIngredient8() {
        return strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public String getStrSource() {
        return strSource;
    }

    public String getStrImageSource() {
        return strImageSource;
    }

    public String getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public String getDateModified() {
        return dateModified;
    }

    public ArrayList<String> getIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();
        if (getStrIngredient1() != null && !getStrIngredient1().equals("")) {
            ingredients.add(getStrIngredient1());
        }
        if (getStrIngredient2() != null && !getStrIngredient2().equals("")) {
            ingredients.add(getStrIngredient2());
        }
        if (getStrIngredient3() != null && !getStrIngredient3().equals("")) {
            ingredients.add(getStrIngredient3());
        }
        if (getStrIngredient4() != null && !getStrIngredient4().equals("")) {
            ingredients.add(getStrIngredient4());
        }
        if (getStrIngredient5() != null && !getStrIngredient5().equals("")) {
            ingredients.add(getStrIngredient5());
        }
        if (getStrIngredient6() != null && !getStrIngredient6().equals("")) {
            ingredients.add(getStrIngredient6());
        }
        if (getStrIngredient7() != null && !getStrIngredient7().equals("")) {
            ingredients.add(getStrIngredient7());
        }
        if (getStrIngredient8() != null && !getStrIngredient8().equals("")) {
            ingredients.add(getStrIngredient8());
        }
        if (getStrIngredient9() != null && !getStrIngredient9().equals("")) {
            ingredients.add(getStrIngredient9());
        }
        if (getStrIngredient10() != null && !getStrIngredient10().equals("")) {
            ingredients.add(getStrIngredient10());
        }
        if (getStrIngredient11() != null && !getStrIngredient11().equals("")) {
            ingredients.add(getStrIngredient11());
        }
        if (getStrIngredient12() != null && !getStrIngredient12().equals("")) {
            ingredients.add(getStrIngredient12());
        }
        if (getStrIngredient13() != null && !getStrIngredient13().equals("")) {
            ingredients.add(getStrIngredient13());
        }
        if (getStrIngredient14() != null && !getStrIngredient14().equals("")) {
            ingredients.add(getStrIngredient14());
        }
        if (getStrIngredient15() != null && !getStrIngredient15().equals("")) {
            ingredients.add(getStrIngredient15());
        }
        if (getStrIngredient16() != null && !getStrIngredient16().equals("")) {
            ingredients.add(getStrIngredient16());
        }
        if (getStrIngredient17() != null && !getStrIngredient17().equals("")) {
            ingredients.add(getStrIngredient17());
        }
        if (getStrIngredient18() != null && !getStrIngredient18().equals("")) {
            ingredients.add(getStrIngredient18());
        }
        if (getStrIngredient19() != null && !getStrIngredient19().equals("")) {
            ingredients.add(getStrIngredient19());
        }
        if (getStrIngredient20() != null && !getStrIngredient20().equals("")) {
            ingredients.add(getStrIngredient20());
        }
        return ingredients;
    }
    //=====
    public ArrayList<String> getMeasures() {
        ArrayList<String> Measures = new ArrayList<>();
        if (getStrMeasure1() != null && !getStrMeasure1().equals("")) {
            Measures.add(getStrMeasure1());
        }
        if (getStrMeasure2() != null && !getStrMeasure2().equals("")) {
            Measures.add(getStrMeasure2());
        }
        if (getStrMeasure3() != null && !getStrMeasure3().equals("")) {
            Measures.add(getStrMeasure3());
        }
        if (getStrMeasure4() != null && !getStrMeasure4().equals("")) {
            Measures.add(getStrMeasure4());
        }
        if (getStrMeasure5() != null && !getStrMeasure5().equals("")) {
            Measures.add(getStrMeasure5());
        }
        if (getStrMeasure6() != null && !getStrMeasure6().equals("")) {
            Measures.add(getStrMeasure6());
        }
        if (getStrMeasure7() != null && !getStrMeasure7().equals("")) {
            Measures.add(getStrMeasure7());
        }
        if (getStrMeasure8() != null && !getStrMeasure8().equals("")) {
            Measures.add(getStrMeasure8());
        }
        if (getStrMeasure9() != null && !getStrMeasure9().equals("")) {
            Measures.add(getStrMeasure9());
        }
        if (getStrMeasure10() != null && !getStrMeasure10().equals("")) {
            Measures.add(getStrMeasure10());
        }
        if (getStrMeasure11() != null && !getStrMeasure11().equals("")) {
            Measures.add(getStrMeasure11());
        }
        if (getStrMeasure12() != null && !getStrMeasure12().equals("")) {
            Measures.add(getStrMeasure12());
        }
        if (getStrMeasure13() != null && !getStrMeasure13().equals("")) {
            Measures.add(getStrMeasure13());
        }
        if (getStrMeasure14() != null && !getStrMeasure14().equals("")) {
            Measures.add(getStrMeasure14());
        }
        if (getStrMeasure15() != null && !getStrMeasure15().equals("")) {
            Measures.add(getStrMeasure15());
        }
        if (getStrMeasure16() != null && !getStrMeasure16().equals("")) {
            Measures.add(getStrMeasure16());
        }
        if (getStrMeasure17() != null && !getStrMeasure17().equals("")) {
            Measures.add(getStrMeasure17());
        }
        if (getStrMeasure18() != null && !getStrMeasure18().equals("")) {
            Measures.add(getStrMeasure18());
        }
        if (getStrMeasure19() != null && !getStrMeasure19().equals("")) {
            Measures.add(getStrMeasure19());
        }
        if (getStrMeasure20() != null && !getStrMeasure20().equals("")) {
            Measures.add(getStrMeasure20());
        }
        return Measures;
    }
    //=====

    // Getters and Setters
    public String getIdMeal() { return idMeal; }
    public void setIdMeal(String idMeal) { this.idMeal = idMeal; }

    public String getStrMeal() { return strMeal; }
    public void setStrMeal(String strMeal) { this.strMeal = strMeal; }

    public String getStrCategory() { return strCategory; }
    public void setStrCategory(String strCategory) { this.strCategory = strCategory; }

    public String getStrArea() { return strArea; }
    public void setStrArea(String strArea) { this.strArea = strArea; }

    public String getStrInstructions() { return strInstructions; }
    public void setStrInstructions(String strInstructions) { this.strInstructions = strInstructions; }

    public String getStrMealThumb() { return strMealThumb; }
    public void setStrMealThumb(String strMealThumb) { this.strMealThumb = strMealThumb; }

   //@Override
   //public String toString() {
   //    return "POJO_class{" +
   //            "title='" + title + '\'' +
   //            ", price=" + price +
   //            ", ID=" + id +
   //            ", description='" + description + '\'' +
   //            ", img_src='" + thumbnail + '\'' +

   //            '}' + "\n";
   //}

    // Parcelable implementation
    protected POJO_class(Parcel in) {
        idMeal = in.readString();
        strMeal = in.readString();
        strCategory = in.readString();
        strArea = in.readString();
        strInstructions = in.readString();
        strMealThumb = in.readString();
        strIngredient1 = in.readString();
        strIngredient2 = in.readString();
        strIngredient3 = in.readString();
        strIngredient4 = in.readString();
        strIngredient5 = in.readString();
        strIngredient6 = in.readString();
        strIngredient7 = in.readString();
        strIngredient8 = in.readString();
        strIngredient9 = in.readString();
        strIngredient10 = in.readString();
        strIngredient11 = in.readString();
        strIngredient12 = in.readString();
        strIngredient13 = in.readString();
        strIngredient14 = in.readString();
        strIngredient15 = in.readString();
        strIngredient16 = in.readString();
        strIngredient17 = in.readString();
        strIngredient18 = in.readString();
        strIngredient19 = in.readString();
        strIngredient20 = in.readString();
        strMeasure1 = in.readString();
        strMeasure2 = in.readString();
        strMeasure3 = in.readString();
        strMeasure4 = in.readString();
        strMeasure5 = in.readString();
        strMeasure6 = in.readString();
        strMeasure7 = in.readString();
        strMeasure8 = in.readString();
        strMeasure9 = in.readString();
        strMeasure10 = in.readString();
        strMeasure11 = in.readString();
        strMeasure12 = in.readString();
        strMeasure13 = in.readString();
        strMeasure14 = in.readString();
        strMeasure15 = in.readString();
        strMeasure16 = in.readString();
        strMeasure17 = in.readString();
        strMeasure18 = in.readString();
        strMeasure19 = in.readString();
        strMeasure20 = in.readString();
        strSource = in.readString();
        strImageSource = in.readString();
        strCreativeCommonsConfirmed = in.readString();
        dateModified = in.readString();
        strYoutube = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idMeal);
        dest.writeString(strMeal);
        dest.writeString(strCategory);
        dest.writeString(strArea);
        dest.writeString(strInstructions);
        dest.writeString(strMealThumb);
        dest.writeString(strIngredient1);
        dest.writeString(strIngredient2);
        dest.writeString(strIngredient3);
        dest.writeString(strIngredient4);
        dest.writeString(strIngredient5);
        dest.writeString(strIngredient6);
        dest.writeString(strIngredient7);
        dest.writeString(strIngredient8);
        dest.writeString(strIngredient9);
        dest.writeString(strIngredient10);
        dest.writeString(strIngredient11);
        dest.writeString(strIngredient12);
        dest.writeString(strIngredient13);
        dest.writeString(strIngredient14);
        dest.writeString(strIngredient15);
        dest.writeString(strIngredient16);
        dest.writeString(strIngredient17);
        dest.writeString(strIngredient18);
        dest.writeString(strIngredient19);
        dest.writeString(strIngredient20);
        dest.writeString(strMeasure1);
        dest.writeString(strMeasure2);
        dest.writeString(strMeasure3);
        dest.writeString(strMeasure4);
        dest.writeString(strMeasure5);
        dest.writeString(strMeasure6);
        dest.writeString(strMeasure7);
        dest.writeString(strMeasure8);
        dest.writeString(strMeasure9);
        dest.writeString(strMeasure10);
        dest.writeString(strMeasure11);
        dest.writeString(strMeasure12);
        dest.writeString(strMeasure13);
        dest.writeString(strMeasure14);
        dest.writeString(strMeasure15);
        dest.writeString(strMeasure16);
        dest.writeString(strMeasure17);
        dest.writeString(strMeasure18);
        dest.writeString(strMeasure19);
        dest.writeString(strMeasure20);
        dest.writeString(strSource);
        dest.writeString(strImageSource);
        dest.writeString(strCreativeCommonsConfirmed);
        dest.writeString(dateModified);
        dest.writeString(strYoutube);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<POJO_class> CREATOR = new Parcelable.Creator<POJO_class>() {
        @Override
        public POJO_class createFromParcel(Parcel in) {
            return new POJO_class(in);
        }

        @Override
        public POJO_class[] newArray(int size) {
            return new POJO_class[size];
        }
    };

}

