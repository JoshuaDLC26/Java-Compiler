// =================================================================================================================================
public class AssignmentOperator extends Operator {
// =================================================================================================================================


    
    // =============================================================================================================================
    public AssignmentOperator (Token token) {
	
        super(token);
	
    }
    // =============================================================================================================================

    
    // =============================================================================================================================
    @Override
    public Type verify (Expression... operands) {

	// Verify the operands, which must be performed in the order in which they will be evaluated (right, then left), to
	// correctly account for use (r-value) and then assignment (l-value).
	Type t0 = operands[0].verify_l();
	Type t1 = operands[1].verify();
        if (t0 == null || t1 == null) Utility.abort("AssignmentOperator.verify(): Operand has null type");

        // Currently, we use convention that void* can be cast to any other ptr type This can be changed, but currently is used so
        // that memory expression's result can be assigned to pointers of any type; changing this requires giving memory
        // expression's result a different unique type, like "mem" or something like that
        Type void_type = new TypeVoid(_position);
        if ( (t0.getPointedType() != null) && (void_type.equals(t1.getPointedType())) ) {
            //technically should only warn if t0 isn't also a pointer to void expressions
            if (!void_type.equals(t0.getPointedType())) {
		Utility.warn("Implicitly casting void* type to " + t0.toString() + " for assignment at " + _position);
	    }
        } else if (!t0.equals(t1)) {
	    Utility.error("Cannot assign expression of type " + t1.toString() + " to l-value of type " + t0.toString(),
			  operands[1]._position);
	}

        return t0.duplicateType(_position);

    } // verify ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    @Override
    public String toAssembly (Expression... operands) {

        String assembly;

        // Evaluate the right-side expression, pushing its value to the top of the stack
        assembly = operands[1].toAssembly();

        // Evaluate the left-side expression, pushing the address of the l-value.
        assembly += operands[0].toAssembly_l();

	// Assign, pushing the value assigned onto the stack as a result of this expression.
	assembly += ( """
		      \tpop\trbx\t\t; Location to be assigned
		      \tpop\trax\t\t; Value to assign
		      \tmov\t[rbx],\trax\t; Assign r-value into l-value
		      \tpush\trax\t\t; Result of assignment expression
		      """ );
	    
        return assembly;

    } // toAssembly ()
    // =============================================================================================================================


	
// =================================================================================================================================
} // class AssignmentOperator
// =================================================================================================================================
