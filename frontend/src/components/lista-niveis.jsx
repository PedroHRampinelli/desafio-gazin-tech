import React, { useEffect, useState } from "react"
import { Table } from "react-bootstrap"
import { deleteNivel, findAllNiveis, getAllDevsByNivel, updateNivel } from "../service"

import ModalDelete from "./modal/modal-delete";
import ModalUpdate from "./modal/modal-update";
import { useFormik } from "formik";

import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";

const ListaNiveis = () => {

    const [atualizarNivel, setAtualizarNivel] = useState({
        nivel: 1
    });
    const [niveis, setNiveis] = useState([]);
    const [excluirNivel, setExcluirNivel] = useState();
    const [isModalOpenDelete, setIsModalOpenDelete] = useState(false);
    const [isModalUpdate, setIsModalUpdate] = useState(false);
    const [quantidades, setQuantidades] = useState({});

    const findNiveis = async () => {
        const response = await findAllNiveis();

        setNiveis(response);
    }

    useEffect(() => {
        findNiveis();
    }, []);

    const formik = useFormik({
        initialValues: setAtualizarNivel,

        onSubmit: values => {
            confirmarAtualizacao(values);
        },
    });

    const handleClickAtualizar = (nivel) => {

        setAtualizarNivel({ ...nivel, id: nivel.id })
        formik.setValues(nivel);
        setIsModalUpdate(true);
    }

    const confirmarAtualizacao = async (nivel) => {
        await updateNivel(nivel);
        setIsModalUpdate(false);
        findNiveis();
    }

    const handleClickExcluir = async (id) => {
        setIsModalOpenDelete(true);
        setExcluirNivel(id);
    };

    const confirmarExclusao = async (id) => {
        await deleteNivel(id);
        setIsModalOpenDelete(false);
        findNiveis();
    };

    const getQuantidadeDev = async (nivelId) => {

        const allDevsByNivel = await getAllDevsByNivel(nivelId);
        setQuantidades((prev) => ({
            ...prev,
            [nivelId]: allDevsByNivel
        }));

    };

    useEffect(() => {
        if (niveis && niveis.length > 0) {
            niveis.forEach((nivel) => {
                getQuantidadeDev(nivel.id);
            });
        }
    }, [niveis]);

    return (
        <>
            {
                isModalOpenDelete && < ModalDelete
                    onHide={() => setIsModalOpenDelete(false)}
                    onDelete={() => confirmarExclusao(excluirNivel)} />
            }
            <>
                {isModalUpdate && (
                    <ModalUpdate
                        onHide={() => setIsModalUpdate(false)}
                        onUpdate={() => confirmarAtualizacao(atualizarNivel)}
                        niveis={niveis}
                        formik={formik}
                        modalType="nivel"
                    />
                )
                }

                <div className="container mt-5">
                    <div className="col-md-12">
                        <h2>Níveis</h2>
                    </div>
                    <div className="col-md-12">


                        <Table >
                            <thead>
                                <tr>
                                    <th>Nível</th>
                                    <th className="text-center">Qtd. Desenvolvedores</th>
                                    <th>
                                        <Button className="btnAdicionar" variant="success">
                                            <Link as={Link} to="/cadastro-nivel">
                                                Adicionar
                                            </Link>
                                        </Button>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                {niveis && niveis.length >= 0 ? (
                                    niveis.map((nivel, index) => (
                                        <tr key={index}>
                                            <td>{nivel.nivel}</td>
                                            <td className="text-center">{quantidades[nivel.id] ?? 0}</td>
                                            <td>
                                                <Button className="btnAtualizar" variant="warning" onClick={() => handleClickAtualizar(nivel)}>Atualizar</Button>
                                                <Button variant="danger" onClick={() => handleClickExcluir(nivel.id)}>Excluir</Button>
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

export default ListaNiveis;