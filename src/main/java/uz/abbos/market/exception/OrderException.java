package uz.abbos.market.exception;

public class OrderException extends RuntimeException{
    public OrderException(String text){
        super(text);
    }
}
