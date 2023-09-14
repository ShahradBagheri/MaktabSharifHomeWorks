package service.impl;

import service.RentContractService;

import java.util.regex.Pattern;

public class RentContractServiceImpl implements RentContractService {

    @Override
    public boolean validContractNumber(String contractNumber) {

        return Pattern.matches("[0-9]{8}",contractNumber);
    }
}
