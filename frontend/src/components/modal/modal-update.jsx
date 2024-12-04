import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function ModalUpdate(props) {
    return (
        <>
            <Modal
                show={true}
                backdrop="static"
                keyboard={false}
                centered
                size="lg"
            >
                <Modal.Header closeButton>
                    <Modal.Title>
                        {props.modalType === "devs" ? "Atualizar Desenvolvedor" : "Atualizar Nível"}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form
                        onSubmit={props.formik.handleSubmit}
                        className="d-flex flex-column align-items-center formStyle"
                    >
                        {props.modalType === "devs" && (
                            <>
                                <div className="mb-3 w-75">
                                    <label htmlFor="nome" className="form-label">Nome</label>
                                    <input
                                        id="nome"
                                        name="nome"
                                        type="text"
                                        required="true"
                                        className="form-control"
                                        onChange={props.formik.handleChange}
                                        value={props.formik.values.nome}
                                    />
                                </div>

                                <div className="mb-3 w-75">
                                    <label htmlFor="sexo" className="form-label">Sexo</label>
                                    <select
                                        id="sexo"
                                        name="sexo"
                                        className="form-select"
                                        onChange={props.formik.handleChange}
                                        value={props.formik.values.sexo}
                                    >
                                        <option value="M">Masculino</option>
                                        <option value="F">Feminino</option>
                                    </select>
                                </div>

                                <div className="mb-3 w-75">
                                    <label htmlFor="dataNascimento" className="form-label">Data de Nascimento</label>
                                    <input
                                        id="dataNascimento"
                                        name="dataNascimento"
                                        type="date"
                                        className="form-control"
                                        onChange={props.formik.handleChange}
                                        value={props.formik.values.dataNascimento}
                                    />
                                </div>

                                <div className="mb-3 w-75">
                                    <label htmlFor="hobby" className="form-label">Hobby</label>
                                    <input
                                        id="hobby"
                                        name="hobby"
                                        type="text"
                                        required="true"
                                        className="form-control"
                                        onChange={props.formik.handleChange}
                                        value={props.formik.values.hobby}
                                    />
                                </div>

                                <div className="mb-3 w-75">
                                    <label htmlFor="nivelId" className="form-label">Nível</label>
                                    <select
                                        id="nivelId"
                                        name="nivelId"
                                        className="form-select"
                                        onChange={props.formik.handleChange}
                                        value={props.formik.values.nivelId}
                                    >
                                        {props.niveis.map((nivel, index) => (
                                            <option value={nivel.id} key={index}>
                                                {nivel.nivel}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </>
                        )}

                        {props.modalType === "nivel" && (
                            <div className="mb-3 w-75">
                                <label htmlFor="nivel" className="form-label">Nível</label>
                                <input
                                    id="nivel"
                                    name="nivel"
                                    type="text"
                                    className="form-control"
                                    required="true"
                                    onChange={props.formik.handleChange}
                                    value={props.formik.values.nivel}
                                />
                            </div>
                        )}

                        <Modal.Footer>
                            <Button variant="secondary" onClick={props.onHide}>
                                Cancelar
                            </Button>
                            <Button variant="primary" type="submit">
                                Atualizar
                            </Button>
                        </Modal.Footer>
                    </form>
                </Modal.Body>
            </Modal>
        </>
    );
}

export default ModalUpdate;
