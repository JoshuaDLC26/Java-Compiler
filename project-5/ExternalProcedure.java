// =================================================================================================================================
// IMPORTS
import java.util.List;
// =================================================================================================================================



// =================================================================================================================================
/**
 * The declaration of an externally defined procedure.
 */
public class ExternalProcedure extends Procedure {
// =================================================================================================================================


    
    // =============================================================================================================================
    /**
     * Basic constructor for an ExternalProcedure.
     *
     * @param name       The procedure's name.
     * @param type       The proecdure's return type.
     * @param parameters The parameters.
     * @param locals     The local variables.
     */
    public ExternalProcedure (Token name, Type type, List<VariableDeclaration> parameters, List<VariableDeclaration> locals) {

        super(name, type, parameters, locals);

    } // ExternalProcedure
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Generate assembly for this procedure.  Externally defined procedures yield no assembly.
     *
     * @return the code-segment assembly for this procedure.
     */
    public String toAssembly () {

	return new String();

    } // toAssembly ()
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Generate assembly for any statically allocated elements in this procedure.  Externally defined procedures have no static
     * elements.
     *
     * @return the statics-segment assembly for this procedure.
     */
    public String toStatics () {

	return new String();

    } // toStatics ();
    // =============================================================================================================================
    


// =================================================================================================================================
} // ExternalProcedure
// =================================================================================================================================
