// =================================================================================================================================
// IMPORTS
import java.util.List;
// =================================================================================================================================



// =================================================================================================================================
/**
 * An operator that calls a procedure.
 */
public class CallOperator extends Operator {
// =================================================================================================================================


    
    // =============================================================================================================================
    // DATA MEMBERS

    /** The name of the procedure; used later for binding. */
    public final String           _name;
    
    /** The procedure whose execution is being initiated by this procedure call. */
    private      Procedure        _procedure;
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Basic constructor for a procedure call.
     * 
     * @param position  The location in the source code at which the expression begins.
     * @param name      The name of the procedure to be called; binding to a declaration occurs later.
     */
    public ProcedureCall (int position, String name) {

	super(position);
	_name = name;

	// The procedure is determined later, during verification (see bind()).
	_procedure = null; 

    } // CallOperator ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Whether this expression can have a reference to itself.
     *
     * @return Whether this operation's operator yields a reference.
     */
    public boolean hasReference () {

        // TODO
        return false;

    } // hasReference ()
    // =============================================================================================================================

    

    // =============================================================================================================================
    /**
     * Binds this procedureCall to the Procedure object to which it corresponds (i.e. sets _procedure)
     * Also forces arguments to bind() as well
     *
     * @param symbols The symbol table of variable declarations.
     */
    public void bind (Map<String, Procedure> symbols) {
	
        // TODO
	
    } // bind ()
    // =============================================================================================================================

    
    
    // =============================================================================================================================
    /**
     * Should set the _return_type field of this expression based on the _procedure field
     * Should also force arguments to verify() as well
     *
     * @return the type produced by evaluation.
     */
    public Type verify () {

        // TODO
        return null;
	
    } // verify ()
    // =============================================================================================================================

    
    // =============================================================================================================================
    /**
     * Return the type of produced by evaluating this expression
     *
     * @return the type when evaluated.
     */
    public Type getType () {

        // TODO
        return null;

    } // getType ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Generate assembly code that will execute this expression.
     *
     * @return the generated assembly code.
     */
    public String toAssembly () {

        // TODO
        return null;

    } // toAssembly ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Generate assembly code for any statics that are part of this expression.  The operands may generate statics, so recur to
     * them.
     *
     * @return the generated assembly code.
     */
    public String toStatics () {

        // TODO
        return null;

    } // toStatics ()
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Verify that the right number of operands is being provided, and then verify any needed properties about those operands.
     *
     * @return the type that this operator will yield given the operands.
     */
    @Override
    public Type verify_l () {

        // TODO
        return null;
	
    } // verify_l ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Generate assembly that will evaluate this operator as an l-value.
     * 
     * @return the generated assembly code.
     */
    @Override
    public String toAssembly_l () {

        // TODO
        return null;
	
    } // toAssembly_l ()
    // =============================================================================================================================


    // =============================================================================================================================
    public String toString () {

        // TODO
        return null;
	
    } // toString ()
    // =============================================================================================================================



// =================================================================================================================================
} // class CallOperator
// =================================================================================================================================
