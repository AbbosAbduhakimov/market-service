package uz.abbos.market.order.controller;

import uz.abbos.market.order.dto.OrderItemDto;
import uz.abbos.market.order.model.OrderItem;
import uz.abbos.market.order.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orderItems")
@AllArgsConstructor
public class OrderItemController {

    private OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid OrderItemDto orderItemDto){
        OrderItem result =orderItemService.create(orderItemDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        OrderItem result= orderItemService.getEntity(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid OrderItemDto orderItemDto){
        boolean result = orderItemService.update(id, orderItemDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result= orderItemService.delete(id);
        return ResponseEntity.ok(result);
    }
}
