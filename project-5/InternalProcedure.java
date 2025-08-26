// =================================================================================================================================
// IMPORTS
import java.util.List;
// =================================================================================================================================



// =================================================================================================================================
/**
 * An object storing data about procedures (name, return type, parameter list, local declaration list)
 */
public class InternalProcedure extends Procedure {
// =================================================================================================================================

    
    
    // =============================================================================================================================
    // DATA MEMBERS

    /** The body statement. */
    public final Statement _body;
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Basic constructor for an InternalProcedure.
     *
     * @param name       The procedure's name.
     * @param type       The proecdure's return type.
     * @param parameters The parameters.
     * @param locals     The local variables.
     * @param body       The code body.
     */
    public InternalProcedure (Token name, Type type, List<VariableDeclaration> parameters, List<VariableDeclaration> locals, Statement body){

        super(name, type, parameters, locals);
	_body = body;

    } // InternalProcedure ()
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Generate assembly for this procedure.
     *
     * @return the code-segment assembly for this procedure.
     */
    public String toAssembly () {

	// TODO: Emit assembly to define this procedure.

    } // toAssembly ()
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Generate assembly for any statically allocated elements in this procedure.
     *
     * @return the statics-segment assembly for this procedure.
     */
    public String toStatics () {

	// Define any statics contained in the procedure body.
	return _body.toStatics();

    } // toStatics ();
    // =============================================================================================================================
    


// =================================================================================================================================
} // class InternalProcedure
// =================================================================================================================================
