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

public class ExprFunc
{
    public static DoubleUnaryOperator convertExpr(String expr)
    {
        return (double x) ->
        {
            ExprEvaluator util = new ExprEvaluator( false, (short)100 );

            String exprToEval = "N(" + expr + " /. x -> " + Double.toString( x ) + ")";

            IExpr result = util.eval( exprToEval );
            String symjaStr = result.toString().replace("`", "").trim();

            // if it's an invalid number, return a number so large that it can't be plotted.
            if ( symjaStr.contains("I") ) return 9999999999999999999999.0;

            return Double.parseDouble( result.toString().replaceAll( "\\*10\\^([+-]?\\d+)", "E$1" ) );
        };
    }
}