package com.solvd.banksystem.bank;

import com.solvd.banksystem.bank.currency.Currency;

public interface Exchangable {

    double USD_BUY = 2.495;
    double USD_SELL = 2.515;
    double EURO_BUY = 2.9;
    double EURO_SELL = 2.912;
    double RUB_BUY = 3.42;
    double RUB_SELL = 3.46;
    double USD_TO_EURO = 0.86;
    double USD_TO_RUB = 72.17;
    double EURO_TO_USD = 1.16;
    double EURO_TO_RUB = 83.50;
    double RUB_TO_USD = 0.0138;
    double RUB_TO_EURO = 0.012;

    Currency exchangeToUsd(Currency currency);

    Currency exchangeToEuro(Currency currency);

    Currency exchangeToRub(Currency currency);

    Currency exchangeToByn(Currency currency);

}
