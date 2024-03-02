package com.vrp.qachallenge.utils;

import com.vrp.qachallenge.models.OrgAccountModel;
import org.testng.annotations.DataProvider;

import java.util.List;

public class OrgAccountDataProvider {

    @DataProvider(name = "getTestData")
    public Object[][] getTestData() {
        List<OrgAccountModel> testDataList = CSVReader.readCSV("OrgAccountsData.csv", OrgAccountModel.class);

        Object[][] testDataArray = new Object[testDataList.size()][1];
        for (int i = 0; i < testDataList.size(); i++) {
            testDataArray[i][0] = testDataList.get(i);
        }

        return testDataArray;
    }

}
