import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

const FormNivel = ({ formik }) => {
    return (
        <form onSubmit={formik.handleSubmit} className="container mt-4 formNivelStyle">
            <div className="row g-3">
                <div className="col-md-6">
                    <label htmlFor="nivel" className="form-label">Nível</label>
                    <input
                        id="nivel"
                        name="nivel"
                        className="form-control"
                        onChange={formik.handleChange}
                        value={formik.values.nivel.nivel}
                        required
                    />
                </div>
                <div className="mt-5 mr-3 col-md-6 ">
                    <Button variant="primary" type="button" className="btnVoltar">
                        <Link as={Link} to="/lista-niveis" >
                            Voltar
                        </Link>
                    </Button >
                    <Button variant="success" type="submit">Criar</Button>
                </div>
            </div>

        </form>
    );
};

export default FormNivel;
