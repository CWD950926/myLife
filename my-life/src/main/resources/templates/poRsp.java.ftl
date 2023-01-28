package com.module.${cfg.module}.dto.${entity?lower_case};

import ${package.Entity}.${entity};
import lombok.Data;

import java.io.Serializable;


@Data
public class ${entity}Rsp extends ${entity} implements Serializable {

}

