/*
 * Copyright (c) 2025 Weather. All Rights Reserved.
 *
 * This code is for educational purposes only.
 * Unauthorized copying, distribution, or modification is prohibited.
 */

package com.slope.math;

import java.util.function.DoubleUnaryOperator;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

public class ExprDiffFunc
{
    public static DoubleUnaryOperator slopeFunc(String expr, double at)
    {
        return (double x) ->
        {
            String diffExpr = "D(" + expr + ", x)";
            double slope;
            var orig = ExprFunc.convertExpr(expr);
            var diff = ExprFunc.convertExpr(diffExpr);
            slope = diff.applyAsDouble(at);
            return slope * (x - at) + orig.applyAsDouble(at);
        };
    }
}