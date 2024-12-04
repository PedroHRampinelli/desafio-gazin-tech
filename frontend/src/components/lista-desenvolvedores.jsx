import React, { useEffect, useState } from "react"
import { Table } from "react-bootstrap"
import { deleteDesenvolvedor, findAllDevs, findAllNiveis, updateDesenvolvedor } from "../service"
import { toast } from "react-toastify";
import ModalDelete from "./modal/modal-delete";
import ModalUpdate from "./modal/modal-update";
import { useFormik } from "formik";
import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";

const ListaDesenvolvedores = () => {

    const [desenvolvedores, setDesenvolvedores] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [atualizarDev, setAtualizarDev] = useState({
        id: 0,
        nome: '',
        sexo: 'M',
        dataNascimento: '',
        hobby: '',
        nivelId: 0
    });
    const [niveis, setNiveis] = useState([]);
    const [excluirDev, setExcluirDev] = useState();
    const [isModalOpenDelete, setIsModalOpenDelete] = useState(false);
    const [isModalUpdate, setIsModalUpdate] = useState(false);

    const findNiveis = async () => {
        const response = await findAllNiveis();

        setNiveis(response);
    }

    const findDesenvolvedores = async () => {
        const response = await findAllDevs()

        setDesenvolvedores(response);
    }

    const handleClickAtualizar = (desenvolvedor) => {
        setAtualizarDev({ ...desenvolvedor })
        formik.setValues(desenvolvedor)

        setIsModalUpdate(true);

    }

    const confirmarAtualizacao = (desenvolvedor) => {

        updateDesenvolvedor(desenvolvedor).then(() => {
            setIsModalUpdate(false);
            findDesenvolvedores()
        })

    }

    const confirmarExclusao = (id) => {
        deleteDesenvolvedor(id).then(() => {
            setIsModalOpenDelete(false);
            toast.success("Desenvolvedor excluido com sucesso")
            findDesenvolvedores()
        })
    }

    const handleClickExcluir = async (id) => {
        setIsModalOpenDelete(true);
        setExcluirDev(id);
    };

    const formik = useFormik({
        initialValues: {
            nome: '',
            sexo: 'M',
            dataNascimento: '',
            hobby: '',
            nivelId: 1
        },

        onSubmit: values => {
            confirmarAtualizacao(values);
        },
    });

    useEffect(() => {
        findNiveis();
        findDesenvolvedores();
    }, [isModalOpen])

    return (
        <>
            {
                isModalOpenDelete && < ModalDelete
                    onHide={() => setIsModalOpenDelete(false)}
                    onDelete={() => confirmarExclusao(excluirDev)}
                    modalType="devs" />
            }
            <>
                {isModalUpdate && (
                    <ModalUpdate
                        onHide={() => setIsModalUpdate(false)}
                        atualizarDev={() => setAtualizarDev()}
                        onUpdate={() => confirmarAtualizacao(atualizarDev)}
                        niveis={niveis}
                        formik={formik}
                        modalType="devs"
                    />
                )
                }

                <div className="container mt-5">
                    <div className="col-md-12">
                        <h2>Desenvolvedores</h2>
                    </div>
                    <div className="col-md-12">
                        <Table className="table">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>Sexo</th>
                                    <th>Data de Nascimento</th>
                                    <th>Hobby</th>
                                    <th>Nível</th>
                                    <th>
                                        <Button className="btnAdicionar" variant="success">
                                            <Link as={Link} to="/cadastro-desenvolvedor">
                                                Adicionar
                                            </Link>
                                        </Button>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                {desenvolvedores && desenvolvedores.length >= 0 ? (
                                    desenvolvedores.map((desenvolvedor, index) => (

                                        <tr key={index}>
                                            <td>{desenvolvedor.nome}</td>
                                            <td>{desenvolvedor.sexo}</td>
                                            <td>{desenvolvedor.dataNascimento}</td>
                                            <td>{desenvolvedor.hobby}</td>
                                            <td>{niveis.find((nivel) => nivel.id === desenvolvedor.nivelId)?.nivel || "Nível não encontrado"}</td>
                                            <td>
                                                <Button className="btnAtualizar" variant="warning" onClick={() => handleClickAtualizar(desenvolvedor)}>Atualizar</Button>
                                                <Button variant="danger" onClick={() => handleClickExcluir(desenvolvedor.id)}>Excluir</Button>
                                            </td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan="6" style={{ textAlign: "center" }}>Nenhum registro encontrado.</td>
                                    </tr>
                                )}
                            </tbody>
                        </Table>
                    </div>
                </div>
            </>
        </>
    )
}

export default ListaDesenvolvedores;
