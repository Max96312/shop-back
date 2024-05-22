//package max.shop.repository.querydsl;
//
//import com.querydsl.core.types.dsl.BooleanExpression;
//import max.shop.domain.Order;
//import max.shop.domain.QMember;
//import max.shop.domain.QOrder;
//import max.shop.domain.type.OrderStatus;
//import max.shop.dto.request.order.OrderSearchRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
//public class OrderRepositoryCustomImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {
//
//    public OrderRepositoryCustomImpl() {
//        super(Order.class);
//    }
//
//    @Override
//    public List<Order> findOrders(OrderSearchRequest searchRequest, Pageable pageable) {
//
//        QOrder order = QOrder.order;
//        QMember user = QMember.member;
//
//        return from(order)
//                .join(order.member, user)
//                .where(statusEq(searchRequest.getOrderStatus()), nameLike(searchRequest.getUserName()))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//    }
//
//    private static BooleanExpression nameLike(String userName) {
//        if(!StringUtils.hasText(userName)){
//            return null;
//        }
//        return QMember.member.name.like(userName);
//    }
//
//    private BooleanExpression statusEq(OrderStatus statusCond){
//        if(statusCond == null){
//            return null;
//        }
//        return QOrder.order.status.eq(statusCond);
//    }
//}
