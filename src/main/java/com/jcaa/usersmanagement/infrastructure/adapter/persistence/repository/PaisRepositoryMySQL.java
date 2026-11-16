package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeletePaisPort;
import com.jcaa.usersmanagement.application.port.out.GetAllPaisesPort;
import com.jcaa.usersmanagement.application.port.out.GetPaisByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetPaisByNombrePort;
import com.jcaa.usersmanagement.application.port.out.SavePaisPort;
import com.jcaa.usersmanagement.application.port.out.UpdatePaisPort;
import com.jcaa.usersmanagement.domain.exception.PaisNotFoundException;
import com.jcaa.usersmanagement.domain.model.PaisModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.PaisPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.PaisPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaisRepositoryMySQL
        implements SavePaisPort,
        UpdatePaisPort,
        GetPaisByIdPort,
        GetPaisByNombrePort,
        GetAllPaisesPort,
        DeletePaisPort {

    private static final String SQL_INSERT =
            "INSERT INTO paises "
                    + "(id, nombre, presidente, continente, nombre_himno, num_habitantes, "
                    + "num_departamentos, num_municipios, idioma_principal, num_universidades, tipo_democracia, created_at, updated_at) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE =
            "UPDATE paises SET nombre = ?, presidente = ?, continente = ?, nombre_himno = ?, "
                    + "num_habitantes = ?, num_departamentos = ?, num_municipios = ?, idioma_principal = ?, "
                    + "num_universidades = ?, tipo_democracia = ?, updated_at = NOW() WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, nombre, presidente, continente, nombre_himno, num_habitantes, "
                    + "num_departamentos, num_municipios, idioma_principal, num_universidades, tipo_democracia "
                    + "FROM paises WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_BY_NOMBRE =
            "SELECT id, nombre, presidente, continente, nombre_himno, num_habitantes, "
                    + "num_departamentos, num_municipios, idioma_principal, num_universidades, tipo_democracia "
                    + "FROM paises WHERE nombre = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, nombre, presidente, continente, nombre_himno, num_habitantes, "
                    + "num_departamentos, num_municipios, idioma_principal, num_universidades, tipo_democracia "
                    + "FROM paises ORDER BY nombre ASC";

    private static final String SQL_DELETE = "DELETE FROM paises WHERE id = ?";

    private final DataSource dataSource;

    @Override
    public PaisModel save(final PaisModel pais) {
        final PaisPersistenceDto dto = PaisPersistenceMapper.fromModelToDto(pais);
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, dto.id());
            stmt.setString(2, dto.nombre());
            stmt.setString(3, dto.presidente());
            stmt.setString(4, dto.continente());
            stmt.setString(5, dto.nombreHimno());
            stmt.setLong(6, dto.numHabitantes());
            stmt.setInt(7, dto.numDepartamentos());
            stmt.setInt(8, dto.numMunicipios());
            stmt.setString(9, dto.idiomaPrincipal());
            stmt.setInt(10, dto.numUniversidades());
            stmt.setString(11, dto.tipoDemocracia());
            stmt.executeUpdate();
        } catch (final SQLException ex) {
            throw PersistenceException.becauseSaveFailed(dto.id(), ex);
        }
        return getById(pais.getId())
                .orElseThrow(() -> PaisNotFoundException.becauseIdWasNotFound(pais.getId()));
    }

    @Override
    public PaisModel update(final PaisModel pais) {
        final PaisPersistenceDto dto = PaisPersistenceMapper.fromModelToDto(pais);
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, dto.nombre());
            stmt.setString(2, dto.presidente());
            stmt.setString(3, dto.continente());
            stmt.setString(4, dto.nombreHimno());
            stmt.setLong(5, dto.numHabitantes());
            stmt.setInt(6, dto.numDepartamentos());
            stmt.setInt(7, dto.numMunicipios());
            stmt.setString(8, dto.idiomaPrincipal());
            stmt.setInt(9, dto.numUniversidades());
            stmt.setString(10, dto.tipoDemocracia());
            stmt.setString(11, dto.id());
            stmt.executeUpdate();
        } catch (final SQLException ex) {
            throw PersistenceException.becauseUpdateFailed(dto.id(), ex);
        }
        return getById(pais.getId())
                .orElseThrow(() -> PaisNotFoundException.becauseIdWasNotFound(pais.getId()));
    }

    @Override
    public Optional<PaisModel> getById(final String id) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setString(1, id);
            final ResultSet rs = stmt.executeQuery();
            return rs.next() ? Optional.of(PaisPersistenceMapper.fromResultSetToModel(rs)) : Optional.empty();
        } catch (final SQLException ex) {
            throw PersistenceException.becauseFindByIdFailed(id, ex);
        }
    }

    @Override
    public Optional<PaisModel> getByNombre(final String nombre) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_NOMBRE)) {
            stmt.setString(1, nombre);
            final ResultSet rs = stmt.executeQuery();
            return rs.next() ? Optional.of(PaisPersistenceMapper.fromResultSetToModel(rs)) : Optional.empty();
        } catch (final SQLException ex) {
            throw PersistenceException.becauseFindByIdFailed(nombre, ex);
        }
    }

    @Override
    public List<PaisModel> getAll() {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet rs = stmt.executeQuery();
            return PaisPersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException ex) {
            throw PersistenceException.becauseFindAllFailed(ex);
        }
    }

    @Override
    public void delete(final String id) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (final SQLException ex) {
            throw PersistenceException.becauseDeleteFailed(id, ex);
        }
    }
}