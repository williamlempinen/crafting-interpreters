package org.craftinginterpreters.lox;

public class RpnPrinter implements Expr.Visitor<String> {
    public String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return rpn(expr.left) + " " + rpn(expr.right) + " " + expr.operator.lexeme;
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return rpn(expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return rpn(expr.right) + " " + expr.operator.lexeme;
    }

    private String rpn(Expr expr) {
        return expr.accept(this);
    }

}
