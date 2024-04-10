package org.craftinginterpreters.lox;

public class RpnPrinter implements Expr.Visitor<String> {
    public String print(Expr expr) {
        return expr.accept(this);
    }

    //no impl
    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return null;
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
    public String visitLogicalExpr(Expr.Logical expr) {
        return null;
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return rpn(expr.right) + " " + expr.operator.lexeme;
    }

    //might not follow the rpn standards
    @Override
    public String visitTernaryExpr(Expr.Ternary expr) {
        return rpn(expr.condition) + ", " + rpn(expr.thenBranch) + ", " + rpn(expr.elseBranch) + " ?";
    }

    //won't be implemented
    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return null;
    }

    private String rpn(Expr expr) {
        return expr.accept(this);
    }

}
