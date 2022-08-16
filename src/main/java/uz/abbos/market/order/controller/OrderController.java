package uz.abbos.market.order.controller;

import uz.abbos.market.order.dto.OrderDto;
import uz.abbos.market.order.model.Order;
import uz.abbos.market.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid OrderDto orderDto){
        Order result =orderService.create(orderDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        OrderDto result =orderService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid OrderDto orderDto){
        boolean result =orderService.update(id, orderDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result= orderService.delete(id);
        return ResponseEntity.ok(result);
    }
}
