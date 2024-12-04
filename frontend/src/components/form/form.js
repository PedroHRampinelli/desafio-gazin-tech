import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

const Form = ({ formik, niveis }) => {
    return (
        <form onSubmit={formik.handleSubmit} className="container mt-4 formStyle">
            <div className="row g-3">
                <div className="col-md-6">
                    <label htmlFor="nome" className="form-label">Nome</label>
                    <input
                        id="nome"
                        name="nome"
                        className="form-control"
                        onChange={formik.handleChange}
                        value={formik.values.nome}
                        required
                    />
                </div>

                <div className="col-md-6">
                    <label htmlFor="sexo" className="form-label">Sexo</label>
                    <select
                        id="sexo"
                        name="sexo"
                        className="form-select"
                        onChange={formik.handleChange}
                        value={formik.values.sexo}
                    >
                        <option value="M">Masculino</option>
                        <option value="F">Feminino</option>
                    </select>
                </div>

                <div className="col-md-6">
                    <label htmlFor="dataNascimento" className="form-label">Data de Nascimento</label>
                    <input
                        id="dataNascimento"
                        name="dataNascimento"
                        type="date"
                        className="form-control"
                        onChange={formik.handleChange}
                        value={formik.values.dataNascimento}
                        required
                    />
                </div>

                <div className="col-md-6">
                    <label htmlFor="hobby" className="form-label">Hobby</label>
                    <input
                        id="hobby"
                        name="hobby"
                        className="form-control"
                        onChange={formik.handleChange}
                        value={formik.values.hobby}
                    />
                </div>

                <div className="col-md-6">
                    <label htmlFor="nivelId" className="form-label">Nível</label>
                    <select
                        id="nivelId"
                        name="nivelId"
                        className="form-select"
                        onChange={formik.handleChange}
                        value={formik.values.nivelId}
                    >
                        {niveis.map((nivel, index) => (
                            <option value={nivel.id} key={index}>{nivel.nivel}</option>
                        ))}
                    </select>
                </div>
            </div>

            <div className="mt-4 d-flex justify-content-end gap-2">
                <Button variant="primary" type="button" className="btnVoltar">
                    <Link as={Link} to="/lista-desenvolvedores">
                        Voltar
                    </Link>

                </Button>
                <Button variant="success" type="submit">Criar</Button>
            </div>
        </form>
    );
};

export default Form;
