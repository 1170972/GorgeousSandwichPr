package pt.isep.arqsoft.GorgeousSandwich.dto.sandwich;

import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.Designation;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.Sandwich;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.Stock;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.Type;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SandwichConverter {

    public SandwichDTO convertToDTO(Sandwich sandwich){
        if (sandwich.obtainSandwichID() == null){
            return new SandwichDTO(sandwich.obtainStock().obtainUnits(), sandwich.obtainType().toString(),
                    sandwich.obtainDesignation().obtainName(), sandwich.obtainDescription().obtainName());
        }
        return new SandwichDTO(sandwich.obtainSandwichID().obtainID(), sandwich.obtainStock().obtainUnits(), sandwich.obtainType().toString(),
                sandwich.obtainDesignation().obtainName(), sandwich.obtainDescription().obtainName());

    }

    public Sandwich convertFromDTO(SandwichDTO sandwichDTO){
        String type = sandwichDTO.type.toUpperCase();
        if(Type.lookupByName(type)== null){
            return null;
        }
        if(sandwichDTO.sandwichId == null) {
            return new Sandwich(Type.valueOf(type), Stock.valueOf(sandwichDTO.stock),
                    Designation.valueOf(sandwichDTO.designation), Description.valueOf(sandwichDTO.description));
        }
        return new Sandwich(Type.valueOf(type), Stock.valueOf(sandwichDTO.stock),
                Designation.valueOf(sandwichDTO.designation), Description.valueOf(sandwichDTO.description), SandwichID.valueOf(sandwichDTO.sandwichId));
    }

    public List<SandwichDTO> convertListToDTO(List<Sandwich> sandwichList){
        List<SandwichDTO> sandwichDTOList = new ArrayList<>();
        for (Sandwich s : sandwichList){
            sandwichDTOList.add(convertToDTO(s));
        }
        return sandwichDTOList;
    }

}
